package org.misalen.workflow.activiti.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.misalen.workflow.activiti.user.MyGroupManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
	static final String NAME = "master";

	// 注入数据源和事务管理器
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource,
			PlatformTransactionManager transactionManager, SpringAsyncExecutor springAsyncExecutor) throws IOException {
		SpringProcessEngineConfiguration processEngineConfiguration = this
				.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
		List<SessionFactory> customSessionFactories = new ArrayList<SessionFactory>();
		customSessionFactories.add(new MyGroupManagerFactory());
		processEngineConfiguration.setCustomSessionFactories(customSessionFactories);
		return processEngineConfiguration;
	}

	// @Bean(name = "processEngineConfiguration")
	// public ProcessEngineConfigurationImpl processEngineConfiguration() {
	// SpringProcessEngineConfiguration processEngineConfiguration = new
	// SpringProcessEngineConfiguration();
	// List<SessionFactory> customSessionFactories = new
	// ArrayList<SessionFactory>();
	// customSessionFactories.add(new MyGroupManagerFactory());
	// processEngineConfiguration.setCustomSessionFactories(customSessionFactories);
	// return processEngineConfiguration;
	// }
	// @Bean
	// @Order(90)
	// InitializingBean usersAndGroupsInitializer(final
	// SpringProcessEngineConfiguration processEngineConfiguration) {
	//
	// return new InitializingBean() {
	// public void afterPropertiesSet() throws Exception {
	// List<SessionFactory> customSessionFactories = new
	// ArrayList<SessionFactory>();
	// customSessionFactories.add(new MyGroupManagerFactory());
	// processEngineConfiguration.setCustomSessionFactories(customSessionFactories);
	//
	// }
	// };
	// }
}
