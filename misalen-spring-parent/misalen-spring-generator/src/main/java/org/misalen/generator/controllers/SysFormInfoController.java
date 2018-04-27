package org.misalen.generator.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.generator.domain.SysFormInfo;
import org.misalen.generator.service.SysFormFieldService;
import org.misalen.generator.service.SysFormInfoService;
import org.misalen.web.controllers.BaseController;
import org.misalen.web.service.SysConversionService;
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
@RequestMapping("/sys/form/info")
public class SysFormInfoController extends BaseController {
	@Autowired
	public SysFormInfoService sysFormInfoService;
	@Autowired
	public SysConversionService sysConversionService;
	@Autowired
	public SysFormFieldService sysFormFieldService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/form/info/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = SysFormInfo.class, excludes = { "sysFormFields" }), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysFormInfo> pageFrom) {
		return renderSuccess(sysFormInfoService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/form/info/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysFormInfo sysFormInfo) {
		sysFormInfo.setAddTime(new Date());
		sysFormInfoService.save(sysFormInfo);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/form/info/update");
		SysFormInfo model = sysFormInfoService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysFormInfo sysFormInfo) {
		sysFormInfoService.save(sysFormInfo);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysFormInfoService.delete(primaryKey);
		return renderSuccess();
	}

	@GetMapping("/code/{primaryKey}")
	public ModelAndView code(@PathVariable String primaryKey) throws IOException {
		File directory = new File(new File("").getAbsolutePath()).getParentFile();
		if (!directory.getName().equals("misalen-spring-parent")) {
			return jumpFailure();
		}
		ModelAndView andView = new ModelAndView("sys/form/code/index");
		SysFormInfo model = sysFormInfoService.get(primaryKey);
		andView.addObject("projectName", getProjectName(directory));
		andView.addObject("model", model);
		return andView;
	}


}
