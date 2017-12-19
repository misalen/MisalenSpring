package org.misalen.generator.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.generator.domain.SysFormField;
import org.misalen.generator.service.SysFormFieldService;
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
@RequestMapping("/sys/form/designer")
public class SysFormDesignerController extends BaseController {

	@Autowired
	public SysFormFieldService sysFormFieldService;

	@GetMapping("/{primaryKey}")
	public ModelAndView home(@PathVariable String primaryKey) {
		ModelAndView andView = new ModelAndView("sys/form/designer/index");
		andView.addObject("primaryKey", primaryKey);
		return andView;
	}

	@PostMapping("/save/{primaryKey}")
	@Transactional
	public @ResponseBody RestResult<?> add(@PathVariable String primaryKey, @RequestBody List<SysFormField> formFields) {
		sysFormFieldService.deleteByFormId(primaryKey);
		for (SysFormField sysFormField : formFields) {
			sysFormField.setFormId(primaryKey);
			sysFormFieldService.save(sysFormField);
		}
		return renderSuccess();
	}

	@PostMapping("/get/{primaryKey}")
	public @ResponseBody RestResult<?> get(@PathVariable String primaryKey) {
		return renderSuccess(sysFormFieldService.findByFormId(primaryKey));
	}
}
