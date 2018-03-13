package org.misalen.workflow.activiti.main.controller;

import org.activiti.engine.runtime.ProcessInstance;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.web.controllers.BaseController;
import org.misalen.workflow.activiti.main.service.MyRuntimeService;
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
@RequestMapping("workflow/runtime")
public class RuntimeController extends BaseController {

	@Autowired
	MyRuntimeService myRuntimeService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("/workflow/runtime/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = ProcessInstance.class,includes={"id"}), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<?> pageFrom) {
		PageFrom<ProcessInstance> models = myRuntimeService.findByPage(pageFrom);
		return renderSuccess(models);
	}

	@GetMapping("/change-state/{processInstanceId}/{isAlive}")
	public @ResponseBody RestResult<?> changeState(@PathVariable String processInstanceId,
			@PathVariable Boolean isAlive) {
		myRuntimeService.changeState(processInstanceId, isAlive);
		return renderSuccess();
	}
}
