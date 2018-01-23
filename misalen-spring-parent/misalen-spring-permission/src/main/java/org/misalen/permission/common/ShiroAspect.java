package org.misalen.permission.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ShiroAspect {
	@Autowired
	private ShiroManage shiroManage;
	@Autowired
	private ShiroRealmDb shiroRealmDb;

	@After("@annotation(updatePerm)")
	public void updatePerm(JoinPoint point, UpdatePerm updatePerm) throws Throwable {
		if (updatePerm.value()) {
			shiroManage.updateSystemPermission();
		}
	}

	@Before("@annotation(updateUser)")
	public void beforeTest(JoinPoint point, UpdateUser updateUser) throws Throwable {
		if (updateUser.value()) {
			shiroRealmDb.updateRole();
		}
	}

	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface UpdatePerm {
		boolean value() default true;
	}

	@Target({ ElementType.TYPE, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface UpdateUser {
		boolean value() default true;
	}
}
