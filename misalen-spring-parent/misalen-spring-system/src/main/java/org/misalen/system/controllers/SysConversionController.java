package org.misalen.system.controllers;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.system.domain.SysConversion;
import org.misalen.system.service.SysConversionService;
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
 * @author DO.VIS
 *
 */
@Controller
@RequestMapping("/sys/conversion")
public class SysConversionController extends BaseController {

	@Autowired
	public SysConversionService sysConversionService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("/sys/conversion/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysConversion> pageFrom) {
		return renderSuccess(sysConversionService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/sys/conversion/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysConversion sysConversion) {
		sysConversionService.save(sysConversion);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("/sys/conversion/update");
		SysConversion model = sysConversionService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysConversion sysConversion) {
		sysConversionService.save(sysConversion);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysConversionService.delete(primaryKey);
		return renderSuccess();
	}
	
}
