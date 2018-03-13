package org.misalen.workflow.activiti.main.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.activiti.engine.repository.Model;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.common.utils.TextUtil;
import org.misalen.web.controllers.BaseController;
import org.misalen.workflow.activiti.main.domain.WorkflowRepository;
import org.misalen.workflow.activiti.main.service.MyRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("workflow/repository")
public class RepositoryController extends BaseController {

	@Autowired
	MyRepositoryService myRepositoryService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("/workflow/repository/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<?> pageFrom) {
		PageFrom<Model> models = myRepositoryService.findByPage(pageFrom);
		return renderSuccess(models);
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/workflow/repository/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(WorkflowRepository workflowRepository) throws UnsupportedEncodingException {
		Model model = myRepositoryService.bulidModel(workflowRepository);
		String modelid = model.getId();
		myRepositoryService.saveModel(modelid);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		myRepositoryService.delete(primaryKey);
		return renderSuccess();
	}

	@GetMapping("/deployment/{id}")
	public @ResponseBody RestResult<?> deployment(@PathVariable("id") String id)
			throws JsonProcessingException, IOException {
		String error = myRepositoryService.deployment(id);
		if (TextUtil.isNullOrEmpty(error)) {
			return renderSuccess();
		} else {
			return renderError(error);
		}
	}

}
