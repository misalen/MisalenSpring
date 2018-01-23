package org.misalen.web.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.misalen.common.advice.structure.RestResult;
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
	
}
