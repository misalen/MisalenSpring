package org.misalen.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializedFields {
	SerializedField[] value() default {};
	String explain() default "";
	int sort() default Integer.MAX_VALUE;

}
