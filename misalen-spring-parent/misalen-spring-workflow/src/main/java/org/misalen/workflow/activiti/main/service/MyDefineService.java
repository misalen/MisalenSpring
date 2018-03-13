package org.misalen.workflow.activiti.main.service;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.misalen.common.utils.PageFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyDefineService {
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 获取模型
	 * 
	 * @param pageFrom
	 * @return
	 */
	public PageFrom<ProcessDefinition> findByPage(PageFrom<?> pageFrom) {
		int firstResult = (pageFrom.getPage() - 1) * pageFrom.getRows();
		int maxResults = firstResult + pageFrom.getRows();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> models = processDefinitionQuery.listPage(firstResult, maxResults);
		PageFrom<ProcessDefinition> from = new PageFrom<ProcessDefinition>();
		from.setList(models);
		from.setTotal(processDefinitionQuery.count());
		return from;
	}

	/**
	 * 启动一个流程
	 *
	 * @param id
	 * @return
	 */
	public void startThe(String processDefinitionId) {
		runtimeService.startProcessInstanceById(processDefinitionId);
	}

}