package org.misalen.generator.controllers;

import java.util.Date;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.generator.domain.SysFlowInfo;
import org.misalen.generator.service.SysFlowInfoService;
import org.misalen.system.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author DO·VIS
 *
 */
@Controller
@RequestMapping("/sys/flow/info")
public class SysFlowInfoController extends BaseController {

	@Autowired
	public SysFlowInfoService sysFlowInfoService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/flow/info/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysFlowInfo> pageFrom) {
		return renderSuccess(sysFlowInfoService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/flow/info/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysFlowInfo sysFlowInfo) {
		sysFlowInfo.setAddTime(new Date());
		sysFlowInfoService.save(sysFlowInfo);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/flow/info/update");
		SysFlowInfo model = sysFlowInfoService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysFlowInfo sysFlowInfo) {
		sysFlowInfoService.save(sysFlowInfo);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysFlowInfoService.delete(primaryKey);
		return renderSuccess();
	}
	
}
