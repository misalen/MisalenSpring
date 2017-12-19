package org.misalen.system.controllers;

import java.util.Date;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.aop.ShiroAspect.UpdatePerm;
import org.misalen.common.utils.PageFrom;
import org.misalen.system.domain.SysResources;
import org.misalen.system.domain.SysResources.SaveType;
import org.misalen.system.service.SysResourcesService;
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
 * BaseController
 * 
 * @author guochao
 *
 */
@Controller
@RequestMapping("/sys/resources")
public class SysResourcesController extends BaseController {

	@Autowired
	public SysResourcesService sysResourcesService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/resources/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = SysResources.class, excludes = { "sysRoleResources", "nodes" })//
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysResources> pageFrom) {
		pageFrom.addAsc("seq");
		pageFrom.addAsc("addTime");
		return renderSuccess(sysResourcesService.findPage(pageFrom));
	}

	@GetMapping("/add/{primaryKey}")
	public ModelAndView add(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/resources/add");
		if (!primaryKey.equals("root")) {
			SysResources sysResources = sysResourcesService.get(primaryKey);
			modelAndView.addObject("parent", sysResources);
			if (sysResources.getSaveType().equals(SaveType.menu1.name())) {
				modelAndView.addObject("saveType", SaveType.menu2.name());
			} else {
				modelAndView.addObject("saveType", SaveType.button.name());
			}
		} else {
			modelAndView.addObject("saveType", SaveType.menu1.name());
		}
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysResources sysResources) {
		sysResources.setAddTime(new Date());
		sysResources.setUsingState("available");
		sysResourcesService.save(sysResources);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/resources/update");
		SysResources sysResources = sysResourcesService.get(primaryKey);
		modelAndView.addObject("sysResources", sysResources);
		if (sysResources.getParentId() != null) {
			SysResources sysResources2 = sysResourcesService.get(sysResources.getParentId());
			modelAndView.addObject("parent", sysResources2);
		}
		return modelAndView;
	}

	@UpdatePerm
	@PostMapping("/update")
	public ModelAndView update(SysResources sysResources) {
		sysResourcesService.save(sysResources);
		return jumpSuccess();
	}

	@UpdatePerm
	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysResourcesService.delete(primaryKey);
		return renderSuccess();
	}

}
