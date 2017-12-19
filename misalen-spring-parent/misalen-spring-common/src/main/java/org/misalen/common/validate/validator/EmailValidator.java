package org.misalen.common.validate.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.misalen.common.validate.Validable;
import org.misalen.common.validate.exception.ValidateError;

/**
 * 
 * @ClassName: Email
 * @author: misalen
 * @date: 2016年7月14日 下午3:48:19
 * 
 * @Desc: 邮箱验证
 *
 */
public class EmailValidator implements Validable {

	@Override
	public ValidateError validate(Object info, Field field, Annotation annotation) {
		try {
			field.setAccessible(true);
			Object value = field.get(info);
			String email = String.valueOf(value);
			Pattern rex = Pattern.compile(
					"^([a-zA-Z0-9]{1})+([a-zA-Z0-9_\\.\\-])*\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z]{1})+([a-zA-Z0-9]{0,2})+([a-zA-Z]{1})+$");
			Matcher matcher = rex.matcher(email);
			if (!matcher.find()) {
				return new ValidateError(field, "请输入正确的邮箱地址,字段>>" + field.getName());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

}
