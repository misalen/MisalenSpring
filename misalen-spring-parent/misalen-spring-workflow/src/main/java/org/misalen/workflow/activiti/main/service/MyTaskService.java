package org.misalen.workflow.activiti.main.service;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.misalen.common.utils.PageFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyTaskService {

	@Autowired
	TaskService taskService;

	/**
	 * 获取模型
	 * 
	 * @param pageFrom
	 * @return
	 */
	public PageFrom<Task> findByGroupPage(PageFrom<?> pageFrom, String group) {
		int firstResult = (pageFrom.getPage() - 1) * pageFrom.getRows();
		int maxResults = firstResult + pageFrom.getRows();
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroup(group);
		List<Task> tasksList = taskQuery.listPage(firstResult, maxResults);
		PageFrom<Task> from = new PageFrom<Task>();
		from.setList(tasksList);
		from.setTotal(taskQuery.count());
		return from;
	}

	/**
	 * 获取模型
	 * 
	 * @param pageFrom
	 * @return
	 */
	public PageFrom<Task> findByUserPage(PageFrom<Task> pageFrom, String user) {
		int firstResult = (pageFrom.getPage() - 1) * pageFrom.getRows();
		int maxResults = firstResult + pageFrom.getRows();
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateUser(user);
		List<Task> tasksList = taskQuery.listPage(firstResult, maxResults);
		PageFrom<Task> from = new PageFrom<Task>();
		from.setList(tasksList);
		from.setTotal(taskQuery.count());
		return from;
	}
}