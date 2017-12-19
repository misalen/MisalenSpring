package org.misalen.system.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.shiro.ShiroUser;
import org.springframework.web.servlet.ModelAndView;

/**
 * BaseController
 * 
 * @author guochao
 *
 */
public class BaseController {

	protected static Logger logger;

	public BaseController() {
		logger = LogManager.getLogger(getClass());
	}

	protected RestResult<?> renderError(String msg) {
		return RestResult.error(msg);
	}

	protected RestResult<?> renderSuccess() {
		return RestResult.ok();
	}

	protected RestResult<?> renderSuccess(Object obj) {
		return RestResult.ok(obj);
	}
	protected ModelAndView jumpSuccess() {
		return new ModelAndView("result/success");
	}
	protected ModelAndView jumpFailure() {
		return new ModelAndView("result/failure");
	}
	protected ShiroUser getShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

}
