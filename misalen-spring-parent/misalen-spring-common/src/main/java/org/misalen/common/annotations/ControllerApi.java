package org.misalen.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: ControllerApi
 * @author: misalen
 * @date: 2016年7月14日 下午3:38:45
 * 
 * @Desc: Controller类描述
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerApi {

	String name();

	String explain() default "";

	int sort() default Integer.MAX_VALUE;
}
