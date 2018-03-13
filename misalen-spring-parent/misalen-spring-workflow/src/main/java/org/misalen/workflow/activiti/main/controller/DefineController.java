package org.misalen.workflow.activiti.main.controller;

import org.activiti.engine.repository.ProcessDefinition;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.web.controllers.BaseController;
import org.misalen.workflow.activiti.main.service.MyDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("workflow/define")
public class DefineController extends BaseController {

	@Autowired
	MyDefineService myDefineService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("/workflow/define/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = ProcessDefinition.class, includes = { "id", "name", "key" }), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<?> pageFrom) {
		PageFrom<ProcessDefinition> models = myDefineService.findByPage(pageFrom);
		return renderSuccess(models);
	}

	@GetMapping("/start-the/{processDefinitionId}")
	public @ResponseBody RestResult<?> startThe(@PathVariable String processDefinitionId) {
		myDefineService.startThe(processDefinitionId);
		return renderSuccess();
	}

}
