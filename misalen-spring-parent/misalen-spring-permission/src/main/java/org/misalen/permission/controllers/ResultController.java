package org.misalen.permission.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "result")
public class ResultController {

	@RequestMapping(value = "/success", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView success(String redirect, String msg) {
		ModelAndView andView = new ModelAndView("result/success");
		andView.addObject("msg", msg);
		return andView;
	}

	@RequestMapping(value = "/failure", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView failure(String redirect, String msg) {
		ModelAndView andView = new ModelAndView("result/failure");
		andView.addObject("msg", msg);
		return andView;
	}
	
	@RequestMapping(value = "/unauth", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView unauth() {
		ModelAndView andView = new ModelAndView("result/unauth");
		return andView;
	}

}
