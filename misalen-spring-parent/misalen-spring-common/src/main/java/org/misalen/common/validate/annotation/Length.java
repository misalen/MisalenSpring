package org.misalen.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Length
 * @author: misalen
 * @date: 2016年7月14日 下午3:48:34
 * 
 * @Desc: length 验证
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Length {

	public int min() default -1;

	public int max() default -1;

	public int length() default -1;
}
