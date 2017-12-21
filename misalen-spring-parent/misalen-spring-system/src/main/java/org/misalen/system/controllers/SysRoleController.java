package org.misalen.system.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.shiro.ShiroAspect.UpdatePerm;
import org.misalen.common.utils.PageFrom;
import org.misalen.system.domain.SysRole;
import org.misalen.system.service.SysResourcesService;
import org.misalen.system.service.SysRoleResourcesService;
import org.misalen.system.service.SysRoleService;
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
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {

	@Autowired
	public SysRoleService sysRoleService;

	@Autowired
	public SysResourcesService sysResourcesService;

	@Autowired
	public SysRoleResourcesService sysRoleResourcesService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/role/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = SysRole.class, excludes = { "sysAdminRoles", "sysRoleResources" })//
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysRole> pageFrom) {
		pageFrom.addAsc("addTime");
		return renderSuccess(sysRoleService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/role/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysRole sysRole) {
		sysRole.setAddTime(new Date());
		sysRole.setUsingState("available");
		sysRoleService.save(sysRole);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/role/update");
		SysRole model = sysRoleService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}
	@UpdatePerm
	@PostMapping("/update")
	public ModelAndView update(SysRole sysRole) {
		sysRoleService.save(sysRole);
		return jumpSuccess();
	}

	@UpdatePerm
	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysRoleService.delete(primaryKey);
		return renderSuccess();
	}

	@GetMapping("/resources/{primaryKey}")
	public ModelAndView role(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/role/resources");
		modelAndView.addObject("primaryKey", primaryKey);
		return modelAndView;
	}

	@PostMapping("/resources/{primaryKey}")
	public @ResponseBody RestResult<?> rolePost(@PathVariable String primaryKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resources", sysResourcesService.getAllTree());
		map.put("roleResources", sysResourcesService.findByRoleId(primaryKey));
		return renderSuccess(map);
	}

	@UpdatePerm
	@PostMapping("/update/resources")
	public ModelAndView list(String primaryKey, String[] resourcesIds) {
		sysRoleResourcesService.deleteByRoleId(primaryKey);
		sysRoleResourcesService.addRoleResources(primaryKey, resourcesIds);
		return jumpSuccess();
	}
}
