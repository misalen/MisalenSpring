package org.misalen.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Email
 * @author: misalen
 * @date: 2016年7月14日 下午3:48:19
 * 
 * @Desc: 邮箱验证
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Email {

}
