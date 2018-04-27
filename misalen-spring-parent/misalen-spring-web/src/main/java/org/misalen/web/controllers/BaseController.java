package org.misalen.web.controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

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

	protected String[] getProjectName(File directory) {
		final String fileName = "misalen-spring-.*";
		String[] list = directory.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(fileName);

			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
		return list;
	}
}
