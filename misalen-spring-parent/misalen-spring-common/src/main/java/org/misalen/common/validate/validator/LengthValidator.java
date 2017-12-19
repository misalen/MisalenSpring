package org.misalen.common.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.misalen.common.validate.Validable;
import org.misalen.common.validate.annotation.Length;
import org.misalen.common.validate.exception.ValidateError;

/**
 * 
 * @ClassName: Length
 * @author: misalen
 * @date: 2016年7月14日 下午3:48:34
 * 
 * @Desc: length 验证
 *
 */
public class LengthValidator implements Validable {

	@Override
	public ValidateError validate(Object info, Field field, Annotation annotation) {

		try {
			field.setAccessible(true);
			Object obj = field.get(info);
			if (obj == null) {
				return new ValidateError(field, "必选参数:字段>>" + field.getName());
			}
			Length length = (Length) annotation;
			if (length.length() > 0) {
				if (obj.toString().length() != length.length()) {
					return new ValidateError(field, "需要长度:" + field.getName() + ",字段>>" + length.length());
				}
			} else {
				if (obj.toString().length() < length.min()) {
					return new ValidateError(field, "最小长度:" + field.getName() + ",字段>>" + length.min());
				} else if (obj.toString().length() > length.max()) {
					return new ValidateError(field, "最大长度:" + field.getName() + ",字段>>" + length.max());
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null;
	}

}
