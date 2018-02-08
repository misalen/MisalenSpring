package org.misalen;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ActivitiSpringTest {
	@Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService historyService;
    @Autowired
    private TaskService managementService;
    @Autowired
    private IdentityService identityService;

    @Test
    public void deploy() {
    	Group group = identityService.newGroup("user");
		group.setName("users");
		group.setType("security-role");
		identityService.saveGroup(group);

		User admin = identityService.newUser("admin");
		admin.setPassword("admin");
		identityService.saveUser(admin);

    }

}
