package org.misalen.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段数据权限
 * 
 * @author guochao
 *
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAccess {

	boolean add() default true;
	
	boolean update() default true;

	boolean list() default true;
	
}
