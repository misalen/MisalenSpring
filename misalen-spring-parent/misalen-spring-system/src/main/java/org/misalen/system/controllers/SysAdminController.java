package org.misalen.system.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.common.utils.TextUtil;
import org.misalen.system.domain.SysAdmin;
import org.misalen.system.domain.SysRole;
import org.misalen.system.service.SysAdminRoleService;
import org.misalen.system.service.SysAdminService;
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
@RequestMapping("/sys/admin")
public class SysAdminController extends BaseController {

	@Autowired
	public SysAdminService sysAdminService;
	@Autowired
	public SysRoleService sysRoleService;
	@Autowired
	public SysAdminRoleService sysAdminRoleService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/admin/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = SysAdmin.class, excludes = { "loginPwd", "sysAdminRoles" })//
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysAdmin> pageFrom) {
		pageFrom.addAsc("addTime");
		return renderSuccess(sysAdminService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/admin/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysAdmin sysAdmin) {
		sysAdmin.setAddTime(new Date());
		sysAdminService.save(sysAdmin);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/admin/update");
		SysAdmin model = sysAdminService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysAdmin sysAdmin) {
		if (TextUtil.isNullOrEmpty(sysAdmin.getLoginPwd())) {
			SysAdmin model = sysAdminService.get(sysAdmin.getPrimaryKey());
			sysAdmin.setLoginPwd(model.getLoginPwd());
		}
		sysAdminService.save(sysAdmin);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysAdminService.delete(primaryKey);
		return renderSuccess();
	}

	@GetMapping("/role/{primaryKey}")
	public ModelAndView role(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/admin/role");
		modelAndView.addObject("primaryKey", primaryKey);
		return modelAndView;
	}

	@PostMapping("/role/{primaryKey}")
	@SerializedFields({ //
			@SerializedField(resultClass = SysRole.class, excludes = { "sysAdminRoles", "sysRoleResources" })//
	})
	public @ResponseBody RestResult<?> rolePost(@PathVariable String primaryKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role", sysRoleService.findOrderByAddTime());
		map.put("userRole", sysRoleService.findByAdminId(primaryKey));
		return renderSuccess(map);
	}

	@PostMapping("/update/role")
	public ModelAndView list(String primaryKey, String[] roleIds) {
		sysAdminRoleService.deleteByAdminId(primaryKey);
		sysAdminRoleService.addAdminRole(primaryKey, roleIds);
		return jumpSuccess();
	}
}
