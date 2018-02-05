package org.misalen.common.advice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.misalen.common.advice.structure.ErrorCode;
import org.misalen.common.advice.structure.FilterForm;
import org.misalen.common.advice.structure.FormUtil;
import org.misalen.common.config.JacksonConfig.MyMappingJackson2HttpMessageConverter;
import org.misalen.common.exception.CustomerException;
import org.misalen.common.utils.TextUtil;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Order(1)
@ControllerAdvice(basePackages = "o" + "r" + "g" + "." + "m" + "i" + "s" + "a" + "l" + "e" + "n")
public class FilterRequestBodyAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return MyMappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		if (!inputMessage.getHeaders().containsKey("msialen")) {
			return inputMessage;
		}
		if (inputMessage.getHeaders().get("msialen").get(0).equals("test")) {
			return inputMessage;
		}
		try {
			return new MyHttpInputMessage(inputMessage);
		} catch (Exception e) {
			throw new IOException();
		}
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		if (inputMessage.getHeaders().containsKey("msialen")) {
			FormUtil.validate(body);
		}
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return body;
	}

	private static class MyHttpInputMessage implements HttpInputMessage {

		private final HttpHeaders headers;
		private final InputStream body;

		public MyHttpInputMessage(HttpInputMessage inputMessage) throws Exception {
			this.headers = inputMessage.getHeaders();
			InputStream inputStream = inputMessage.getBody();
			this.body = readSingleJSON(inputStream);
		}

		@Override
		public InputStream getBody() {
			return (this.body != null ? this.body : StreamUtils.emptyInput());
		}

		private InputStream readSingleJSON(InputStream inputStream) throws Exception {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode jsonNode = mapper.readTree(inputStream);
				JsonNode filter = jsonNode.get("filter");
				JsonParser jsonParser = mapper.treeAsTokens(filter);
				FilterForm filterForm = mapper.readValue(jsonParser, FilterForm.class);
				String sign = encrypt(filterForm);
				if (checkSign(sign, filterForm.getSign())) {
					return new ByteArrayInputStream(jsonNode.get("data").toString().getBytes("UTF-8"));
				} else {
					throw new CustomerException(ErrorCode.SYS_ERROR_CODE_601);
				}
			} catch (Exception e) {
				throw new CustomerException(ErrorCode.SYS_ERROR_CODE_600);
			}
		}

		private static String encrypt(FilterForm filterForm) {
			String imei = filterForm.getImei();
			String macCode = filterForm.getMacCode();
			String nonceStr = filterForm.getNonceStr();
			Long macTime = filterForm.getMacTime();
			String correctKey = TextUtil.MD5(imei + macCode) + TextUtil.MD5(nonceStr + String.valueOf(macTime));
			return TextUtil.MD5(correctKey);
		}

		private static boolean checkSign(String string, String sign) {
			return string.equals(sign);
		}

		@Override
		public HttpHeaders getHeaders() {
			return headers;
		}
	}

}
