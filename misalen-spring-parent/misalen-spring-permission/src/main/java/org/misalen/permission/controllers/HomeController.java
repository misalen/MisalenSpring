package org.misalen.permission.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.misalen.common.utils.TextUtil;
import org.misalen.permission.common.ShiroUser;
import org.misalen.permission.common.ShiroAspect.UpdateUser;
import org.misalen.permission.service.SysAdminService;
import org.misalen.permission.service.SysResourcesService;
import org.misalen.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

	@Autowired
	public SysAdminService sysAdminService;

	@Autowired
	public SysResourcesService sysResourcesService;

	@RequestMapping("/")
	public String home1() {
		return "redirect:/home/";
	}

	@UpdateUser
	@RequestMapping("/home/")
	public ModelAndView home() {
		ShiroUser shiroUser = getShiroUser();
		if (shiroUser == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("nickname",
				TextUtil.isNullOrEmpty(shiroUser.getNickname()) ? shiroUser.getUsername() : shiroUser.getNickname());
		if (shiroUser.getRoleSet() != null && shiroUser.getRoleSet().size() > 0) {
			modelAndView.addObject("userTree", sysResourcesService.getUserMenu(shiroUser.getRoleSet()));
		}
		return modelAndView;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	@PostMapping("/login")
	public ModelAndView loginPost(String username, String password) {
		if (TextUtil.isNullOrEmpty(username)) {
			logger.error("用户名不能为空");
			return new ModelAndView("redirect:/login");
		}
		if (TextUtil.isNullOrEmpty(password)) {
			logger.error("密码不能为空");
			return new ModelAndView("redirect:/login");
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
		token.setRememberMe(true);
		try {
			user.login(token);
		} catch (UnknownAccountException e) {
			logger.error("账号不存在！", e);
			return new ModelAndView("redirect:/login");
		} catch (DisabledAccountException e) {
			logger.error("账号未启用！", e);
			return new ModelAndView("redirect:/login");
		} catch (IncorrectCredentialsException e) {
			logger.error("密码错误！", e);
			return new ModelAndView("redirect:/login");
		} catch (RuntimeException e) {
			logger.error("未知错误,请联系管理员！", e);
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("redirect:/home/");
	}

	protected ShiroUser getShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
}