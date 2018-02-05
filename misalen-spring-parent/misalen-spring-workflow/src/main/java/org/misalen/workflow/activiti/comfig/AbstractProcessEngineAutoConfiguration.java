package org.misalen.workflow.activiti.comfig;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringCallerRunsRejectedJobsHandler;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.SpringRejectedJobsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

public abstract class AbstractProcessEngineAutoConfiguration extends AbstractProcessEngineConfiguration {
	@Autowired
	protected ActivitiProperties activitiProperties;

	@Autowired
	private ResourcePatternResolver resourceLoader;
	@Bean
	public SpringAsyncExecutor springAsyncExecutor(TaskExecutor taskExecutor) {
		return new SpringAsyncExecutor(taskExecutor, springRejectedJobsHandler());
	}
	@Bean
	public SpringRejectedJobsHandler springRejectedJobsHandler() {
		return new SpringCallerRunsRejectedJobsHandler();
	}

	protected SpringProcessEngineConfiguration baseSpringProcessEngineConfiguration(DataSource dataSource,
			PlatformTransactionManager platformTransactionManager, SpringAsyncExecutor springAsyncExecutor)
			throws IOException {
		List<Resource> procDefResources = this.discoverProcessDefinitionResources(this.resourceLoader,
				this.activitiProperties.getProcessDefinitionLocationPrefix(),
				this.activitiProperties.getProcessDefinitionLocationSuffixes(),
				this.activitiProperties.isCheckProcessDefinitions());

		SpringProcessEngineConfiguration conf = super.processEngineConfigurationBean(
				procDefResources.toArray(new Resource[procDefResources.size()]), dataSource, platformTransactionManager,
				springAsyncExecutor);

		conf.setDeploymentName(defaultText(activitiProperties.getDeploymentName(), conf.getDeploymentName()));
		conf.setDatabaseSchema(defaultText(activitiProperties.getDatabaseSchema(), conf.getDatabaseSchema()));
		conf.setDatabaseSchemaUpdate(
				defaultText(activitiProperties.getDatabaseSchemaUpdate(), conf.getDatabaseSchemaUpdate()));

		conf.setDbIdentityUsed(activitiProperties.isDbIdentityUsed());
		conf.setDbHistoryUsed(activitiProperties.isDbHistoryUsed());

		conf.setAsyncExecutorActivate(activitiProperties.isAsyncExecutorActivate());

		conf.setMailServerHost(activitiProperties.getMailServerHost());
		conf.setMailServerPort(activitiProperties.getMailServerPort());
		conf.setMailServerUsername(activitiProperties.getMailServerUserName());
		conf.setMailServerPassword(activitiProperties.getMailServerPassword());
		conf.setMailServerDefaultFrom(activitiProperties.getMailServerDefaultFrom());
		conf.setMailServerUseSSL(activitiProperties.isMailServerUseSsl());
		conf.setMailServerUseTLS(activitiProperties.isMailServerUseTls());

		conf.setHistoryLevel(activitiProperties.getHistoryLevel());

		return conf;
	}
	
	protected String defaultText(String deploymentName, String deploymentName1) {
		if (StringUtils.hasText(deploymentName))
			return deploymentName;
		return deploymentName1;
	}

	@Bean
	public ProcessEngineFactoryBean processEngine(SpringProcessEngineConfiguration configuration) throws Exception {
		return super.springProcessEngineBean(configuration);
	}

	@Bean
	@ConditionalOnMissingBean
	@Override
	public RuntimeService runtimeServiceBean(ProcessEngine processEngine) {
		return super.runtimeServiceBean(processEngine);
	}

	@Bean
	@ConditionalOnMissingBean
	@Override
	public RepositoryService repositoryServiceBean(ProcessEngine processEngine) {
		return super.repositoryServiceBean(processEngine);
	}

	@Bean
	@ConditionalOnMissingBean
	@Override
	public TaskService taskServiceBean(ProcessEngine processEngine) {
		return super.taskServiceBean(processEngine);
	}

	@Bean
	@ConditionalOnMissingBean
	@Override
	public HistoryService historyServiceBean(ProcessEngine processEngine) {
		return super.historyServiceBean(processEngine);
	}

	@Bean
	@ConditionalOnMissingBean
	@Override
	public ManagementService managementServiceBeanBean(ProcessEngine processEngine) {
		return super.managementServiceBeanBean(processEngine);
	}

	@Bean
	@ConditionalOnMissingBean
	public TaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}
}
