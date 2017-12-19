package org.misalen.common.validate.exception;

import java.lang.reflect.Field;

public class ValidationError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationError(Field field, ValidateError formError) {
		this.field = field;
		this.error = formError;
	}

	public Field field;

	public ValidateError error;

	@Override
	public String toString() {
		return "属性:" + field.getName() + ",错误信息:" + error.toString();
	}
}
