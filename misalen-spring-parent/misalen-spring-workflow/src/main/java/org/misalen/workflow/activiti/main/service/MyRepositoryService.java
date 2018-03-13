package org.misalen.workflow.activiti.main.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.misalen.common.utils.PageFrom;
import org.misalen.workflow.activiti.main.domain.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MyRepositoryService {

	@Autowired
	RepositoryService repositoryService;
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 保存模型
	 * 
	 * @param modelid
	 * @throws UnsupportedEncodingException
	 */
	public void saveModel(String modelid) throws UnsupportedEncodingException {
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.set("stencilset", stencilSetNode);
		repositoryService.addModelEditorSource(modelid, editorNode.toString().getBytes("utf-8"));
	}

	/**
	 * 构建模型
	 * 
	 * @param workflowRepository
	 * @return
	 */
	public Model bulidModel(WorkflowRepository workflowRepository) {
		Model model = repositoryService.newModel();
		ObjectNode modelNode = objectMapper.createObjectNode();
		modelNode.put(ModelDataJsonConstants.MODEL_NAME, workflowRepository.getName());
		modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, workflowRepository.getDescription());
		modelNode.put(ModelDataJsonConstants.MODEL_REVISION, workflowRepository.getRevision());
		model.setName(workflowRepository.getName());
		model.setKey(workflowRepository.getKey());
		model.setMetaInfo(modelNode.toString());
		repositoryService.saveModel(model);
		return model;
	}

	/**
	 * 获取模型
	 * 
	 * @param pageFrom
	 * @return
	 */
	public PageFrom<Model> findByPage(PageFrom<?> pageFrom) {
		int firstResult = (pageFrom.getPage() - 1) * pageFrom.getRows();
		int maxResults = firstResult + pageFrom.getRows();
		ModelQuery modelQuery =	repositoryService.createModelQuery();
		List<Model> models = modelQuery.listPage(firstResult, maxResults);
		PageFrom<Model> from = new PageFrom<Model>();
		from.setList(models);
		from.setTotal(modelQuery.count());
		return from;
	}

	/**
	 * 删除模型
	 * 
	 * @param primaryKey
	 */
	public void delete(String primaryKey) {
		repositoryService.deleteModel(primaryKey);
	}

	/**
	 * 发布一个流程
	 * 
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	public String deployment(String id) throws JsonProcessingException, IOException {
		Model modelData = repositoryService.getModel(id);
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
		if (bytes == null) {
			return ("模型数据为空，请先设计流程并成功保存，再进行发布。");
		}
		JsonNode modelNode = new ObjectMapper().readTree(bytes);
		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0) {
			return ("数据模型不符要求，请至少设计一条主线流程。");
		}
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
				.addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);
		return null;

	}

}