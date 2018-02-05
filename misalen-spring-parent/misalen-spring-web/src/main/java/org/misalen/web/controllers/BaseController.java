package org.misalen.web.controllers;

import org.misalen.common.advice.structure.RestResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * BaseController
 * 
 * @author guochao
 *
 */
public class BaseController {

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
