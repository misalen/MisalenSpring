package org.misalen.common.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.misalen.common.validate.Validable;
import org.misalen.common.validate.exception.ValidateError;

/**
 * 
 * @ClassName: MobilePhone
 * @author: misalen
 * @date: 2016年7月14日 下午3:49:04
 * 
 * @Desc: 手机号格式验证
 *
 */
public class MobilePhoneValidator implements Validable {

	@Override
	public ValidateError validate(Object info, Field field, Annotation annotation) {

		try {
			field.setAccessible(true);
			Object value = field.get(info);
			String email = String.valueOf(value);
			Pattern rex = Pattern.compile("^((1[3-9]))\\d{9}$");
			Matcher matcher = rex.matcher(email);
			if (!matcher.find()) {
				return new ValidateError(field, "请输入正确的手机号,字段>>" + field.getName());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
