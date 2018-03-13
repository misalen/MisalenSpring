package org.misalen.workflow.activiti.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

	// 注入数据源和事务管理器
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(//
			CustomGroupEntityManagerFactory customGroupEntityManagerFactory, //
			CustomUserEntityManagerFactory customUserEntityManagerFactory, //
			DataSource dataSource, //
			PlatformTransactionManager transactionManager, //
			SpringAsyncExecutor springAsyncExecutor) throws IOException {
		SpringProcessEngineConfiguration processEngineConfiguration = this
				.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
		List<SessionFactory> customSessionFactories = new ArrayList<SessionFactory>();
		customSessionFactories.add(customGroupEntityManagerFactory);
		customSessionFactories.add(customUserEntityManagerFactory);
		processEngineConfiguration.setCustomSessionFactories(customSessionFactories);
		return processEngineConfiguration;
	}

	@Bean
	public RepositoryService getRepositoryService(ProcessEngine processEngine) {
		return processEngine.getRepositoryService();
	}

	@Bean
	public RuntimeService getRuntimeService(ProcessEngine processEngine) {
		return processEngine.getRuntimeService();
	}

	@Bean
	public TaskService getTaskService(ProcessEngine processEngine) {
		return processEngine.getTaskService();
	}

	@Bean
	public HistoryService getHistoryService(ProcessEngine processEngine) {
		return processEngine.getHistoryService();
	}

	@Bean
	public ManagementService getManagementService(ProcessEngine processEngine) {
		return processEngine.getManagementService();
	}

	@Bean
	public FormService getFormService(ProcessEngine processEngine) {
		return processEngine.getFormService();
	}

}
