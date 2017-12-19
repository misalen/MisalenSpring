package org.misalen.common.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.misalen.common.validate.annotation.Email;
import org.misalen.common.validate.annotation.Length;
import org.misalen.common.validate.annotation.MobilePhone;
import org.misalen.common.validate.annotation.Required;
import org.misalen.common.validate.exception.ValidateError;
import org.misalen.common.validate.validator.EmailValidator;
import org.misalen.common.validate.validator.LengthValidator;
import org.misalen.common.validate.validator.MobilePhoneValidator;
import org.misalen.common.validate.validator.RequiredValidator;

/**
 * 
 * @ClassName: ValidatorFactory
 * @author: misalen
 * @date: 2016年7月14日 下午3:47:33
 * 
 * @Desc: 验证工厂
 *
 */
public class ValidatorFactory {

	public static Validable create(Annotation anno) {
		if (anno instanceof Required) {
			return new RequiredValidator();
		} else if (anno instanceof Length) {
			return new LengthValidator();
		} else if (anno instanceof MobilePhone) {
			return new MobilePhoneValidator();
		} else if (anno instanceof Email) {
			return new EmailValidator();
		}
		return null;
	}

	public static List<ValidateError> validate(Object object) {
		List<ValidateError> errors = new ArrayList<>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annos = field.getDeclaredAnnotations();
			for (Annotation anno : annos) {
				Validable validator = ValidatorFactory.create(anno);
				if (validator != null) {
					ValidateError error = validator.validate(object, field, anno);
					if (error != null) {
						errors.add(error);
					}
				}
			}
		}
		return errors;
	}
}
