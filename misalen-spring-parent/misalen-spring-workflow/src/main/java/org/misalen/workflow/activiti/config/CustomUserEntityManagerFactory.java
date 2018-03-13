package org.misalen.workflow.activiti.config;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomUserEntityManagerFactory implements SessionFactory {

	@Autowired
	private CustomUserEntityManager customUserEntityManager;

	public Class<?> getSessionType() {
		return UserEntityManager.class;
	}

	public Session openSession() {
		return customUserEntityManager;
	}
}
