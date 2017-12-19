package org.misalen;

import java.io.IOException;

import javax.sql.DataSource;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.misalen.workflow.activiti.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(DataSource dataSource,
			PlatformTransactionManager transactionManager, SpringAsyncExecutor springAsyncExecutor) throws IOException {
		return this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
	}
}
