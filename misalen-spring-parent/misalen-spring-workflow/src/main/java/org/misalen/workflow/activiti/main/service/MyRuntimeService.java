package org.misalen.workflow.activiti.main.service;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.misalen.common.utils.PageFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRuntimeService {

	@Autowired
	RuntimeService runtimeService;

	/**
	 * 获取模型
	 * 
	 * @param pageFrom
	 * @return
	 */
	public PageFrom<ProcessInstance> findByPage(PageFrom<?> pageFrom) {
		int firstResult = (pageFrom.getPage() - 1) * pageFrom.getRows();
		int maxResults = firstResult + pageFrom.getRows();
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		List<ProcessInstance> runningList = processInstanceQuery.listPage(firstResult, maxResults);
		PageFrom<ProcessInstance> from = new PageFrom<ProcessInstance>();
		from.setList(runningList);
		from.setTotal(processInstanceQuery.count());
		return from;
	}

	/**
	 * 激活 或 挂起
	 * 
	 * @param processInstanceId
	 * @param isAlive
	 */
	public void changeState(String processInstanceId, Boolean isAlive) {
		if (!isAlive) {
			runtimeService.suspendProcessInstanceById(processInstanceId);
		} else {
			runtimeService.activateProcessInstanceById(processInstanceId);
		}
	}
}