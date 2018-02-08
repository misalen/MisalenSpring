package org.misalen;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.misalen.workflow.activiti.user.MyGroupManagerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@ComponentScan({ "org.activiti.rest.diagram", "org.misalen" })
public class BootWithActivitiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootWithActivitiApplication.class, args);
	}

	@Bean
	@Order(100)
	InitializingBean usersAndGroupsInitializer(final IdentityService identityService,
			final SpringProcessEngineConfiguration processEngineConfiguration) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {

//				List<SessionFactory> customSessionFactories = new ArrayList<SessionFactory>();
//				customSessionFactories.add(new MyGroupManagerFactory());
//				processEngineConfiguration.setCustomSessionFactories(customSessionFactories);

				Group group = identityService.newGroup("user");
				group.setName("users");
				group.setType("security-role");
				identityService.saveGroup(group);

				User admin = identityService.newUser("admin");
				admin.setPassword("admin");
				identityService.saveUser(admin);

			}
		};
	}
}
