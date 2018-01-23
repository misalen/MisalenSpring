package org.misalen.common.advice;

import java.util.ArrayList;
import java.util.List;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Order(1)
@ControllerAdvice(basePackages = "o" + "r" + "g" + "." + "m" + "i" + "s" + "a" + "l" + "e" + "n")
public class FilterResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Boolean hasJsonAnno = returnType.getMethodAnnotation(SerializedField.class) != null;
		if (!hasJsonAnno) {
			hasJsonAnno = returnType.getMethodAnnotation(SerializedFields.class) != null;
		}
		return hasJsonAnno;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof RestResult) {
			RestResult<?> result = (RestResult<?>) body;
			SerializedField serializedField = returnType.getMethodAnnotation(SerializedField.class);
			SerializedFields serializedFields = returnType.getMethodAnnotation(SerializedFields.class);
			List<SerializedField> fields = new ArrayList<SerializedField>();
			if (serializedField != null) {
				fields.add(serializedField);
			}
			if (serializedFields != null && serializedFields.value().length > 0) {
				for (SerializedField serializedField2 : serializedFields.value()) {
					fields.add(serializedField2);
				}
			}
			CustomerJsonSerializer customerJsonSerializer = new CustomerJsonSerializer();
			for (SerializedField serializedField2 : fields) {
				if (serializedField2.resultClass().getName().equals(SerializedField.class.getName())) {
					customerJsonSerializer.filter(result.getData().getClass(), serializedField2.includes(),
							serializedField2.excludes());
				} else {
					customerJsonSerializer.filter(serializedField2.resultClass(), serializedField2.includes(),
							serializedField2.excludes());
				}
			}
			return customerJsonSerializer.toJson(result);
		}
		return body;
	}

	static final String DYNC_INCLUDE = "DYNC_INCLUDE";
	static final String DYNC_EXCLUDE = "DYNC_EXCLUDE";

	@JsonFilter(DYNC_EXCLUDE)
	interface DynamicExclude {
	}

	@JsonFilter(DYNC_INCLUDE)
	interface DynamicInclude {
	}

	class CustomerJsonSerializer {
		ObjectMapper mapper = new ObjectMapper();

		/**
		 * @param clazz
		 *            需要设置规则的Class
		 * @param include
		 *            转换时包含哪些字段
		 * @param filter
		 *            转换时过滤哪些字段
		 */
		public void filter(Class<?> clazz, String[] includes, String[] excludes) {
			if (includes.length > 0) {
				mapper.setFilterProvider(new SimpleFilterProvider().addFilter(DYNC_INCLUDE,
						SimpleBeanPropertyFilter.filterOutAllExcept(includes)));
				mapper.addMixIn(clazz, DynamicInclude.class);
			}
			if (excludes.length > 0) {
				mapper.setFilterProvider(new SimpleFilterProvider().addFilter(DYNC_EXCLUDE,
						SimpleBeanPropertyFilter.serializeAllExcept(excludes)));
				mapper.addMixIn(clazz, DynamicExclude.class);
			}
		}

		public String toJson(Object object) {
			try {
				return mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
