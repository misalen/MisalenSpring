package ${packageName}.${type};

import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import ${packageName}.domain.${name.upperCaseFirstName};
import ${packageName}.service.${name.upperCaseFirstName}Service;
import org.misalen.system.controllers.BaseController;
import org.misalen.common.advice.structure.RestResult;
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
 * 
 * @author DO.VIS
 *
 */
@Controller
@RequestMapping("${path}")
public class ${name.upperCaseFirstName}Controller extends BaseController {

	@Autowired
	public ${name.upperCaseFirstName}Service ${name.lowerCaseFirstName}Service;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("${path}/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<${name.upperCaseFirstName}> pageFrom) {
		return renderSuccess(${name.lowerCaseFirstName}Service.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("${path}/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(${name.upperCaseFirstName} ${name.lowerCaseFirstName}) {
		${name.lowerCaseFirstName}Service.save(${name.lowerCaseFirstName});
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("${path}/update");
		${name.upperCaseFirstName} model = ${name.lowerCaseFirstName}Service.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(${name.upperCaseFirstName} ${name.lowerCaseFirstName}) {
		${name.lowerCaseFirstName}Service.save(${name.lowerCaseFirstName});
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		${name.lowerCaseFirstName}Service.delete(primaryKey);
		return renderSuccess();
	}
	
}
