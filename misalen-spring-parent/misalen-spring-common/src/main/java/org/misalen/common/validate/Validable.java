package org.misalen.common.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.misalen.common.validate.exception.ValidateError;

/**
 * 
 * @ClassName: Validable
 * @author: misalen
 * @date: 2016年7月14日 下午3:46:19
 * 
 * @Desc: 验证接口
 *
 */
public interface Validable {

	public ValidateError validate(Object info, Field field, Annotation annotation) throws RuntimeException;
}
