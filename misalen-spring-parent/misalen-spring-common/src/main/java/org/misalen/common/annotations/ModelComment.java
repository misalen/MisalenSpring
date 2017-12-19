package org.misalen.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: ModelComment
 * @author: misalen
 * @date: 2016年7月14日 下午3:32:00
 * 
 * @Desc: model字段描述
 *
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelComment {
	String value();
}
