package org.misalen.common.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.misalen.common.advice.structure.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = Exception.class)
	public ModelAndView globalException(HttpServletRequest request, HttpServletResponse response, Exception exception)
			throws Exception {
		logger.error(exception.getLocalizedMessage());
		exception.printStackTrace();
		if (!request.getHeader("accept").contains("application/json")) {
			if (exception instanceof NoHandlerFoundException) {
				return new ModelAndView("error/404");
			} else {
				return new ModelAndView("error/500");
			}
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			if (exception instanceof CustomerException) {
				CustomerException customerException = (CustomerException) exception;
				PrintWriter writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(customerException.getRestResult()));
				writer.flush();
			} else if (exception instanceof NoHandlerFoundException) {
				PrintWriter writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(RestResult.error("请求地址错误")));
				writer.flush();
			} else {
				PrintWriter writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(RestResult.error("服务器内部错误")));
				writer.flush();
			}
			return null;
		}
	}

}
