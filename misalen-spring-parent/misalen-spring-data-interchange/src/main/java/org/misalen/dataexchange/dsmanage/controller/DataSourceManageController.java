package org.misalen.dataexchange.dsmanage.controller;

import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.dataexchange.dsmanage.domain.DataSourceManage;
import org.misalen.dataexchange.dsmanage.service.DataSourceManageService;
import org.misalen.system.controllers.BaseController;
import org.misalen.common.advice.structure.RestResult;
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
@RequestMapping("/dataexchange/dsmanage")
public class DataSourceManageController extends BaseController {

	@Autowired
	public DataSourceManageService dataSourceManageService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("/dataexchange/dsmanage/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<DataSourceManage> pageFrom) {
		return renderSuccess(dataSourceManageService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/dataexchange/dsmanage/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(DataSourceManage dataSourceManage) {
		dataSourceManageService.save(dataSourceManage);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("/dataexchange/dsmanage/update");
		DataSourceManage model = dataSourceManageService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(DataSourceManage dataSourceManage) {
		dataSourceManageService.save(dataSourceManage);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		dataSourceManageService.delete(primaryKey);
		return renderSuccess();
	}
	
}
