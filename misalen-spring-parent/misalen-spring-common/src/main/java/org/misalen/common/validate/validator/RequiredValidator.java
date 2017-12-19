package org.misalen.common.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.misalen.common.validate.Validable;
import org.misalen.common.validate.exception.ValidateError;

/**
 * 
 * @ClassName: Required
 * @author: misalen
 * @date: 2016年7月14日 下午3:49:14
 * 
 * @Desc: 必填验证
 *
 */
public class RequiredValidator implements Validable {

	@Override
	public ValidateError validate(Object info, Field field, Annotation annotation) {
		try {
			field.setAccessible(true);
			Object value = field.get(info);
			if (value == null) {
				return new ValidateError(field, "不能为空,字段>>" + field.getName());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
