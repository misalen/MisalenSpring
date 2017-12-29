package org.misalen.generator.controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.generator.domain.SysFormField;
import org.misalen.generator.domain.SysFormInfo;
import org.misalen.generator.domain.SysTableColumn;
import org.misalen.generator.domain.SysTableInfo;
import org.misalen.generator.service.SysFormFieldService;
import org.misalen.generator.service.SysFormInfoService;
import org.misalen.generator.service.SysTableColumnService;
import org.misalen.generator.service.SysTableInfoService;
import org.misalen.hibernate.tool.util.FormTypeToJavaHelper;
import org.misalen.hibernate.tool.util.JavaToFormTypeHelper;
import org.misalen.hibernate.tool.util.Named;
import org.misalen.system.controllers.BaseController;
import org.misalen.system.domain.SysResources;
import org.misalen.system.domain.SysResources.SaveType;
import org.misalen.system.service.SysConversionService;
import org.misalen.system.service.SysResourcesService;
import org.misalen.util.ControllerGenerator;
import org.misalen.util.ModelGenerator;
import org.misalen.util.PageAddGenerator;
import org.misalen.util.PageListGenerator;
import org.misalen.util.PageUpdateGenerator;
import org.misalen.util.RepositoryGenerator;
import org.misalen.util.ServiceGenerator;
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
 * @author DO·VIS
 *
 */
@Controller
@RequestMapping("/sys/form/info")
public class SysFormInfoController extends BaseController {
	@Autowired
	public SysResourcesService sysResourcesService;
	@Autowired
	public SysFormInfoService sysFormInfoService;
	@Autowired
	public SysConversionService sysConversionService;
	@Autowired
	public SysTableInfoService sysTableInfoService;
	@Autowired
	public SysTableColumnService sysTableColumnService;
	@Autowired
	public SysFormFieldService sysFormFieldService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/form/info/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = SysFormInfo.class, excludes = { "sysFormFields" }), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysFormInfo> pageFrom) {
		return renderSuccess(sysFormInfoService.findPage(pageFrom));
	}

	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("sys/form/info/add");
		return modelAndView;
	}

	@PostMapping("/add")
	public ModelAndView add(SysFormInfo sysFormInfo) {
		sysFormInfo.setAddTime(new Date());
		sysFormInfoService.save(sysFormInfo);
		return jumpSuccess();
	}

	@GetMapping("/update/{primaryKey}")
	public ModelAndView update(@PathVariable String primaryKey) {
		ModelAndView modelAndView = new ModelAndView("sys/form/info/update");
		SysFormInfo model = sysFormInfoService.get(primaryKey);
		modelAndView.addObject("model", model);
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(SysFormInfo sysFormInfo) {
		sysFormInfoService.save(sysFormInfo);
		return jumpSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysFormInfoService.delete(primaryKey);
		return renderSuccess();
	}

	@GetMapping("/table/first")
	public ModelAndView tablefirst() {
		ModelAndView andView = new ModelAndView("sys/form/table/first");
		List<SysTableInfo> sysTableInfos = sysTableInfoService.findAll();
		andView.addObject("tableInfos", sysTableInfos);
		return andView;
	}

	@GetMapping("/table/second")
	public ModelAndView tablesecond(String tableId) {
		ModelAndView andView = new ModelAndView("sys/form/table/second");
		SysTableInfo sysTableInfo = sysTableInfoService.get(tableId);
		andView.addObject("model", sysTableInfo);
		return andView;
	}

	@PostMapping("/table/second")
	public ModelAndView tablesecondForm(String tableId, SysFormInfo sysFormInfo) {
		sysFormInfo.setPrimaryKey(null);
		sysFormInfoService.save(sysFormInfo);
		SysTableInfo sysTableInfo = sysTableInfoService.get(tableId);
		int i = 0;
		for (SysTableColumn column : sysTableInfo.getSysTableColumns()) {
			SysFormField field = new SysFormField();
			field.setIndex(++i);
			field.setFormId(sysFormInfo.getPrimaryKey());
			field.setTitle(column.getRemarks());
			field.setType(column.getType());
			field.setMandatory(column.getNullable());
			field.setDecimals(column.getScale());
			field.setLength(column.getLength());
			field.setElementId(JavaToFormTypeHelper.getPreferredFormType(column.getType()));
			if (field.getElementId().equals("code")) {
				field.setDtcode("yesorno");
			}

			sysFormFieldService.save(field);
		}
		return jumpSuccess();
	}

	@GetMapping("/code/{primaryKey}")
	public ModelAndView code(@PathVariable String primaryKey) throws IOException {
		File directory = new File(new File("").getAbsolutePath()).getParentFile();
		if (!directory.getName().equals("misalen-spring-parent")) {
			return jumpFailure();
		}
		ModelAndView andView = new ModelAndView("sys/form/code/index");
		SysFormInfo model = sysFormInfoService.get(primaryKey);
		andView.addObject("projectName", getProjectName(directory));
		andView.addObject("model", model);
		return andView;
	}

	@PostMapping("/code")
	public ModelAndView codePost(SysFormInfo formInfo, //
			String projectName, //
			String[] generatorKeys//
	) throws Exception {
		SysFormInfo model = sysFormInfoService.get(formInfo.getPrimaryKey());
		for (SysFormField sysFormField : model.getSysFormFields()) {
			sysFormField.setNamed(new Named(sysConversionService.findByOriginal(sysFormField.getTitle()).getEscape()));
			sysFormField.setJavaType(FormTypeToJavaHelper.getPreferredFormType(sysFormField.getElementId(),
					sysFormField.getDecimals(), sysFormField.getDtcode()));
		}
		formInfo.setSysFormFields(model.getSysFormFields());
		String moduleName = sysConversionService.findByOriginal(formInfo.getModuleName()).getEscape().toLowerCase();
		String functionName = sysConversionService.findByOriginal(formInfo.getFunctionName()).getEscape().toLowerCase();
		StringBuffer buffer = new StringBuffer();
		buffer.append("org.misalen.");
		buffer.append(moduleName);
		buffer.append(".");
		buffer.append(functionName);
		String packageName = buffer.toString();
		String path = "/" + moduleName + "/" + functionName;
		for (String string : generatorKeys) {
			switch (string) {
			case "model":
				ModelGenerator modelGenerator = new ModelGenerator().setSysFormInfo(formInfo);
				modelGenerator.setPackageName(packageName);
				modelGenerator.setProjectName(projectName);
				modelGenerator.setTemplateName("model.ftl");
				modelGenerator.save();
				break;
			case "repository":
				RepositoryGenerator repositoryGenerator = new RepositoryGenerator().setName(formInfo.getClassName());
				repositoryGenerator.setPackageName(packageName);
				repositoryGenerator.setProjectName(projectName);
				repositoryGenerator.setTemplateName("repository.ftl");
				repositoryGenerator.save();
				break;
			case "service":
				ServiceGenerator serviceGenerator = new ServiceGenerator().setName(formInfo.getClassName());
				serviceGenerator.setPackageName(packageName);
				serviceGenerator.setProjectName(projectName);
				serviceGenerator.setTemplateName("service.ftl");
				serviceGenerator.save();
				break;
			case "controller":
				ControllerGenerator controllerGenerator = new ControllerGenerator()//
						.setPath(path).setName(formInfo.getClassName());
				controllerGenerator.setPackageName(packageName);
				controllerGenerator.setProjectName(projectName);
				controllerGenerator.setTemplateName("controller.ftl");
				controllerGenerator.save();
				break;
			case "view-list":
				PageListGenerator pageListGenerator = new PageListGenerator()//
						.setPath(path).setSysFormInfo(formInfo).setName(formInfo.getClassName());
				pageListGenerator.setPackageName(packageName);
				pageListGenerator.setProjectName(projectName);
				pageListGenerator.setTemplateName("page-list.ftl");
				pageListGenerator.save();
				addMenu(formInfo, moduleName, functionName, path);
				break;
			case "view-add":
				PageAddGenerator pageAddGenerator = new PageAddGenerator()//
						.setPath(path).setSysFormInfo(formInfo).setName(formInfo.getClassName());
				pageAddGenerator.setPackageName(packageName);
				pageAddGenerator.setProjectName(projectName);
				pageAddGenerator.setTemplateName("page-add.ftl");
				pageAddGenerator.save();
				break;
			case "view-update":
				PageUpdateGenerator pageUpdateGenerator = new PageUpdateGenerator()//
						.setPath(path).setSysFormInfo(formInfo).setName(formInfo.getClassName());
				pageUpdateGenerator.setPackageName(packageName);
				pageUpdateGenerator.setProjectName(projectName);
				pageUpdateGenerator.setTemplateName("page-update.ftl");
				pageUpdateGenerator.save();
				break;
			default:
				break;
			}
		}
		return jumpSuccess();
	}

	private void addMenu(SysFormInfo formInfo, String moduleName, String functionName, String path) {
		SysResources parent = sysResourcesService.findByText(formInfo.getModuleName());
		if (parent == null) {
			parent = new SysResources();
			parent.setSaveType(SaveType.menu1.name());
			parent.setText(formInfo.getModuleName());
			parent.setUsingState("available");
			parent.setSeq(0);
			sysResourcesService.save(parent);
		}
		SysResources resources = sysResourcesService.findByTextAndParentId(formInfo.getFunctionName(),
				parent.getPrimaryKey());
		if (resources == null) {
			resources = new SysResources();
			resources.setParentId(parent.getPrimaryKey());
			resources.setSaveType(SaveType.menu2.name());
			resources.setText(formInfo.getFunctionName());
			resources.setUsingState("available");
			resources.setSeq(0);
			resources.setResourceUrl(path+"/");
			sysResourcesService.save(resources);
		}

	}

	private String[] getProjectName(File directory) {
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
