package org.misalen.workflow.activiti.config;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomGroupEntityManagerFactory implements SessionFactory {

	@Autowired
	private CustomGroupEntityManager customGroupEntityManager;
	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return customGroupEntityManager;
	}

}