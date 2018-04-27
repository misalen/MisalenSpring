package org.misalen.generator.controllers;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.misalen.common.annotations.DataAccess;
import org.misalen.common.annotations.Enumeration;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.ObjectUtils;
import org.misalen.generator.domain.SysFormField;
import org.misalen.generator.domain.SysFormInfo;
import org.misalen.generator.service.SysFormFieldService;
import org.misalen.generator.service.SysFormInfoService;
import org.misalen.hibernate.tool.util.FormTypeToJavaHelper;
import org.misalen.hibernate.tool.util.Named;
import org.misalen.permission.domain.SysAdmin;
import org.misalen.util.ControllerGenerator;
import org.misalen.util.ModelGenerator;
import org.misalen.util.PageAddGenerator;
import org.misalen.util.PageListGenerator;
import org.misalen.util.PageUpdateGenerator;
import org.misalen.util.RepositoryGenerator;
import org.misalen.util.ServiceGenerator;
import org.misalen.web.controllers.BaseController;
import org.misalen.web.service.SysConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author DO·VIS
 *
 */
@Controller
@RequestMapping("/sys/code/generation")
public class SysCodeGenerationController extends BaseController {
	@Autowired
	public SysFormInfoService sysFormInfoService;
	@Autowired
	public SysConversionService sysConversionService;
	@Autowired
	public SysFormFieldService sysFormFieldService;

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView andView=	new ModelAndView("sys/code/generation/index");
		File directory = new File(new File("").getAbsolutePath()).getParentFile();
		andView.addObject("projectName", getProjectName(directory));
		return andView;
	}

	@PostMapping("/table")
	public ModelAndView tablePost(SysFormInfo formInfo, //
			String projectName, //
			String[] generatorKeys//
	) throws Exception {
		formInfo.setSysFormFields(bulidField(formInfo.getPrimaryKey()));
		String moduleName = sysConversionService.findByOriginal(formInfo.getModuleName()).getEscape().toLowerCase();
		formInfo.setModuleName(moduleName);
		String functionName = sysConversionService.findByOriginal(formInfo.getFunctionName()).getEscape().toLowerCase();
		formInfo.setFunctionName(functionName);
		StringBuffer buffer = new StringBuffer();
		buffer.append("org.misalen.");
		buffer.append(moduleName);
		buffer.append(".");
		buffer.append(functionName);
		String packageName = buffer.toString();
		generation(generatorKeys, packageName, projectName, formInfo);
		return jumpSuccess();
	}

	@PostMapping("/class")
	public ModelAndView classPost(String clazzName, //
			String projectName, //
			String[] generatorKeys//
	) throws Exception {
		SysFormInfo formInfo = bulidFormInfo(clazzName);
		String moduleName = sysConversionService.findByOriginal(formInfo.getModuleName()).getEscape().toLowerCase();
		formInfo.setModuleName(moduleName);
		String functionName = sysConversionService.findByOriginal(formInfo.getFunctionName()).getEscape().toLowerCase();
		formInfo.setFunctionName(functionName);
		StringBuffer buffer = new StringBuffer();
		buffer.append("org.misalen.");
		buffer.append(moduleName);
		buffer.append(".");
		buffer.append(functionName);
		String packageName = buffer.toString();
		generation(generatorKeys, packageName, projectName, formInfo);
		return jumpSuccess();
	}

	private SysFormInfo bulidFormInfo(String clazzName) throws Exception {
		SysFormInfo formInfo = new SysFormInfo();
		String strs[] = clazzName.split(".");
		formInfo.setClassName(strs[strs.length - 1]);
		formInfo.setFunctionName(strs[strs.length - 2]);
		formInfo.setModuleName(strs[strs.length - 3]);
		formInfo.setSysFormFields(bulidField(Class.forName(clazzName)));
		return formInfo;
	}

	private static Set<SysFormField> bulidField(Class<?> clazz) {
		Set<SysFormField> formFields = new HashSet<SysFormField>();
		Field[] fields = clazz.getDeclaredFields();
		List<Field> fields2=Arrays.asList(fields);
		List<Field> arrList = new ArrayList<Field>(fields2);
		while (clazz.getSuperclass().getName().indexOf("org.misalen.db.jpa.base.domain")>-1) {
			clazz=clazz.getSuperclass();
			arrList.addAll(new ArrayList<Field>(Arrays.asList(clazz.getDeclaredFields())));
		}
		
		for (Field field : arrList) {
			Column column = field.getAnnotation(Column.class);
			if (ObjectUtils.isBaseDataType(field.getType())&& column != null && field.getAnnotation(Transient.class) == null) {
				System.err.println(field.getType());
				SysFormField formField = new SysFormField();
				formField.setNamed(new Named(field.getName()));

				ModelComment comment = field.getAnnotation(ModelComment.class);
				formField.setTitle(comment == null ? field.getName() : comment.value());

				Enumeration enumeration = field.getAnnotation(Enumeration.class);
				if (enumeration != null) {
					formField.setDtcode(enumeration.value());
				}
				formField.setLength(column.length());
				formField.setMandatory(!column.nullable());
				formField.setJavaType(field.getType().getSimpleName());

				DataAccess dataAccess = field.getAnnotation(DataAccess.class);
				if (dataAccess != null) {
					formField.setAccessAdd(dataAccess.add());
					formField.setAccessList(dataAccess.list());
					formField.setAccessSearch(dataAccess.search());
					formField.setAccessUpdate(dataAccess.update());
				} else {
					formField.setAccessAdd(false);
					formField.setAccessList(false);
					formField.setAccessSearch(false);
					formField.setAccessUpdate(false);
				}
				formFields.add(formField);
			}
		}
		return formFields;
	}

	public static void main(String[] args) {
		bulidField(SysAdmin.class);
	}

	private Set<SysFormField> bulidField(String formPrimaryKey) {
		SysFormInfo sysFormInfo = sysFormInfoService.get(formPrimaryKey);
		for (SysFormField sysFormField : sysFormInfo.getSysFormFields()) {
			sysFormField.setNamed(new Named(sysConversionService.findByOriginal(sysFormField.getTitle()).getEscape()));
			sysFormField.setJavaType(FormTypeToJavaHelper.getPreferredFormType(sysFormField.getElementId(),
					sysFormField.getDecimals(), sysFormField.getDtcode()));
		}
		return sysFormInfo.getSysFormFields();
	}

	private void generation(String[] generatorKeys, String packageName, String projectName, SysFormInfo formInfo)
			throws Exception {
		String path = "/" + formInfo.getModuleName() + "/" + formInfo.getFunctionName();
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

	}

//	private void addMenu(SysFormInfo formInfo, String moduleName, String functionName, String path) {
//		// SysResources parent =
//		// sysResourcesService.findByText(formInfo.getModuleName());
//		// if (parent == null) {
//		// parent = new SysResources();
//		// parent.setSaveType(SaveType.menu1.name());
//		// parent.setText(formInfo.getModuleName());
//		// parent.setUsingState("available");
//		// parent.setSeq(0);
//		// sysResourcesService.save(parent);
//		// }
//		// SysResources resources =
//		// sysResourcesService.findByTextAndParentId(formInfo.getFunctionName(),
//		// parent.getPrimaryKey());
//		// if (resources == null) {
//		// resources = new SysResources();
//		// resources.setParentId(parent.getPrimaryKey());
//		// resources.setSaveType(SaveType.menu2.name());
//		// resources.setText(formInfo.getFunctionName());
//		// resources.setUsingState("available");
//		// resources.setSeq(0);
//		// resources.setResourceUrl(path+"/");
//		// sysResourcesService.save(resources);
//		// }
//
//	}
}
