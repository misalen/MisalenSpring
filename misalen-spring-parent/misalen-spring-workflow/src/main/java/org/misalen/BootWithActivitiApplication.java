package org.misalen;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.misalen.common.global.ModuleUtil;
import org.misalen.common.utils.PageFrom;
import org.misalen.common.utils.TextUtil;
import org.misalen.workflow.activiti.main.service.MyTaskService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.activiti.rest.diagram", "org.misalen" })
public class BootWithActivitiApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication application = new SpringApplication(BootWithActivitiApplication.class);
		Map<String, Object> map = new HashMap<>();
		String[] names = ModuleUtil.getPropertiesName();
		String name = TextUtil.join(names);
		map.put("spring.config.name", name);
		application.setDefaultProperties(map);
		application.run(args);
	}

	@Bean
	InitializingBean usersAndGroupsInitializer(final MyTaskService myTaskService) {

		return new InitializingBean() {
			public void afterPropertiesSet() throws Exception {
				//myDefineService.startThe("流程标识");
				//System.err.println("==========="+myDefineService.count());
				String userId = "admin";
				PageFrom<Task> list=	myTaskService.findByUserPage(new PageFrom<Task>(), userId);
//				List<Task> list = processEngine.getTaskService()//
//						.createTaskQuery()//
//						.taskAssignee(userId)// 指定个人任务查询
//						.list();
				for (Task task : list.getList()) {
					System.out.println("id=" + task.getId());
					System.out.println("name=" + task.getName());
					System.out.println("assinee=" + task.getAssignee());
					System.out.println("createTime=" + task.getCreateTime());
					System.out.println("executionId=" + task.getExecutionId());
				}
			}
		};
	}
}
