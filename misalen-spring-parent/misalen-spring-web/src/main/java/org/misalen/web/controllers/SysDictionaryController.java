package org.misalen.web.controllers;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.web.domain.SysDictionary;
import org.misalen.web.domain.SysDictionaryData;
import org.misalen.web.service.SysDictionaryDataService;
import org.misalen.web.service.SysDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends BaseController {

	@Autowired
	public SysDictionaryService sysDictionaryService;

	@Autowired
	public SysDictionaryDataService sysDictionaryDataService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/dictionary/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = SysDictionary.class, excludes = { "sysDictionaryDatas" })//
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysDictionary> pageFrom) {
		pageFrom.addAsc("addTime");
		return renderSuccess(sysDictionaryService.findPage(pageFrom));
	}

	@PostMapping("/all")
	@SerializedFields({ //
			@SerializedField(resultClass = SysDictionary.class, excludes = { "sysDictionaryDatas" })//
	})
	public @ResponseBody RestResult<?> all() {
		return renderSuccess(sysDictionaryService.findAll(new Specification<SysDictionary>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<SysDictionary> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return query.getGroupRestriction();
			}
		}));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/dictionary/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysDictionary sysDictionary) {
		sysDictionary.setAddTime(new Date());
		sysDictionaryService.save(sysDictionary);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/dictionary/update");
		SysDictionary model = sysDictionaryService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysDictionary sysDictionary) {
		sysDictionaryService.save(sysDictionary);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysDictionaryService.delete(primaryKey);
		return renderSuccess();
	}

	@PostMapping("/data/list/{primaryKey}")
	public @ResponseBody RestResult<?> datalist(@PathVariable String primaryKey) {
		return renderSuccess(sysDictionaryDataService.findByDtId(primaryKey));
	}

	@GetMapping("/dataadd/{primaryKey}")
	public ModelAndView dataadd(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/dictionary/dataadd");
		modelAndView.addObject("primaryKey", primaryKey);
		return modelAndView;
	}

	@PostMapping("/dataadd")
	public ModelAndView dataaddPost(SysDictionaryData sysDictionaryData) {
		sysDictionaryData.setAddTime(new Date());
		sysDictionaryDataService.save(sysDictionaryData);
		return jumpSuccess();
	}

	@GetMapping("/dataupdata/{primaryKey}")
	public ModelAndView dataupdate(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/dictionary/dataupdata");
		SysDictionaryData model = sysDictionaryDataService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/dataupdata")
	public ModelAndView dataupdataPost(SysDictionaryData sysDictionaryData) {
		sysDictionaryDataService.save(sysDictionaryData);
		return jumpSuccess();
	}

	@GetMapping("/datadel/{primaryKey}")
	public @ResponseBody RestResult<?> datadel(@PathVariable String primaryKey) {
		sysDictionaryDataService.delete(primaryKey);
		return renderSuccess();
	}
}
