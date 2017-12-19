package org.misalen.common.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: Required
 * @author: misalen
 * @date: 2016年7月14日 下午3:49:14
 * 
 * @Desc: 必填验证
 *
 */
@Target(value = { ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Required {

}
