package org.misalen.common.validate.exception;

import java.lang.reflect.Field;

/**
 * 
 * @ClassName: ValidateError
 * @author: misalen
 * @date: 2016年7月14日 下午3:52:35
 * 
 * @Desc:
 *
 */
public class ValidateError {

	public Field field;

	public ValidateError(Field field, String message) {
		this.field = field;
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
