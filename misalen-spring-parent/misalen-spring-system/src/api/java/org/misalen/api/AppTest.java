package org.misalen.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.misalen.api.model.ApiBody;
import org.misalen.api.model.ApiController;
import org.misalen.api.model.ApiMethod;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.ControllerApi;
import org.misalen.common.annotations.Enumeration;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PageFrom;
import org.misalen.common.validate.annotation.Email;
import org.misalen.common.validate.annotation.Length;
import org.misalen.common.validate.annotation.MobilePhone;
import org.misalen.common.validate.annotation.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	@Autowired
	ApplicationContext context;

	@Test
	public void contextLoads() throws Exception {

		Map<String, Object> map = filter(context.getBeansWithAnnotation(ControllerApi.class));
		LinkedList<ApiController> list = getApiController(map.values());
		String mapJakcson = new ObjectMapper().writeValueAsString(list);
		File file = new File("C:\\Users\\zhaoguochao\\Desktop\\serverapi.js");
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("var apiJson=" + mapJakcson);
		bw.close();
		System.out.println("========  YES  ========");
	}

	private LinkedList<ApiController> getApiController(Collection<Object> collection) throws Exception {
		LinkedList<ApiController> apiControllers = new LinkedList<ApiController>();
		for (Object object : collection) {
			ControllerApi api = object.getClass().getSuperclass().getAnnotation(ControllerApi.class);
			ApiController apiController = new ApiController();
			apiController.setExplain(api.explain());
			apiController.setName(api.name());
			apiController.setSort(api.sort());
			apiController.setDocInterfaces(getApiMethod(object));
			apiControllers.add(apiController);
		}
		return apiControllers;
	}

	private LinkedList<ApiMethod> getApiMethod(Object object) throws Exception {
		LinkedList<ApiMethod> apiMethods = new LinkedList<ApiMethod>();
		RequestMapping requestMapping = object.getClass().getSuperclass().getAnnotation(RequestMapping.class);
		String baseUrl = requestMapping != null ? requestMapping.value()[0] : "";
		Method[] methods = object.getClass().getSuperclass().getDeclaredMethods();
		for (Method method : methods) {
			ApiMethod apiMethod = new ApiMethod();
			PostMapping postMapping = method.getAnnotation(PostMapping.class);
			GetMapping getMapping = method.getAnnotation(GetMapping.class);
			RequestMapping requestMapping2 = method.getAnnotation(RequestMapping.class);
			if (postMapping != null) {
				apiMethod.setMethod(RequestMethod.POST.name());
				apiMethod.setUrl(baseUrl + postMapping.value()[0]);
			} else if (getMapping != null) {
				apiMethod.setMethod(RequestMethod.GET.name());
				apiMethod.setUrl(baseUrl + getMapping.value()[0]);
			} else if (requestMapping2 != null) {
				apiMethod.setMethod(requestMapping2.method()[0].name());
				apiMethod.setUrl(baseUrl + requestMapping2.value()[0]);
			}
			Parameter[] params = method.getParameters();
			method.getParameters()[0].getAnnotation(RequestBody.class);
			for (Parameter param : params) {
				RequestBody body = param.getAnnotation(RequestBody.class);
				if (body != null) {
					apiMethod.setRequestBody(getRequestApiBody(param.getType()));
					break;
				}
			}
			List<SerializedField> fields = getApiMethodAnnotation(method);
			SerializedFields serializedFields = method.getAnnotation(SerializedFields.class);
			if (serializedFields != null && serializedFields.explain().length() > 0) {
				apiMethod.setSort(serializedFields.sort());
				apiMethod.setExplain(serializedFields.explain());
			} else if (fields.size() > 0) {
				apiMethod.setSort(fields.get(0).sort());
				apiMethod.setExplain(fields.get(0).explain());
			}
			apiMethod.setResponseBody(getResponseApiBody(method, fields));
			apiMethods.add(apiMethod);
		}
		return apiMethods;
	}

	private List<SerializedField> getApiMethodAnnotation(Method method) {
		SerializedFields serializedFields = method.getAnnotation(SerializedFields.class);
		LinkedList<SerializedField> fields = new LinkedList<SerializedField>();
		if (serializedFields != null && serializedFields.value().length > 0) {
			for (SerializedField serializedField2 : serializedFields.value()) {
				fields.add(serializedField2);
			}
		}
		SerializedField serializedField = method.getAnnotation(SerializedField.class);
		if (serializedField != null) {
			fields.add(serializedField);
		}
		return fields;
	}

	private List<Class<?>> getResponseClass(Method method) throws ClassNotFoundException {
		List<Class<?>> clazzes = new LinkedList<Class<?>>();
		Type returnType = method.getGenericReturnType();
		if (returnType.toString().startsWith(RestResult.class.getName())) {
			clazzes.add(RestResult.class);
			Type[] clazz = ClazzUtil.getParameterizedTypes(returnType);
			if (clazz[0].toString().startsWith(PageFrom.class.getName())) {
				clazzes.add(ClazzUtil.getClass(PageFrom.class));
				Type[] clazz2 = ClazzUtil.getParameterizedTypes(clazz[0]);
				if (clazz2 != null) {
					clazzes.add(ClazzUtil.getClass(clazz2[0]));
				}
			} else {
				clazzes.add(ClazzUtil.getClass(clazz[0]));
			}
		} else {
			clazzes.add(ClazzUtil.getClass(returnType));
		}
		return clazzes;
	}

	private LinkedList<ApiBody> getResponseApiBody(Method method, List<SerializedField> fields) throws Exception {
		List<Class<?>> clazzes = getResponseClass(method);
		LinkedList<ApiBody> apiBodies = new LinkedList<ApiBody>();
		for (Class<?> clazz : clazzes) {
			if (clazz.isAssignableFrom(PageFrom.class)) {
				apiBodies.addAll(getResponseApiBodyByPageForm(clazz));
			}
		}
		for (Class<?> clazz : clazzes) {
			if (clazz.isAssignableFrom(RestResult.class) || clazz.isAssignableFrom(PageFrom.class)) {
				continue;
			}
			if (apiBodies.isEmpty()) {
				return getResponseApiBody(clazz, fields);
			}
			for (ApiBody apiBody : apiBodies) {
				if (apiBody.getExplain().equals("外层List")) {
					apiBody.setChildren(getResponseApiBody(clazz, fields));
					break;
				}
			}
			return apiBodies;
		}
		return apiBodies;
	}

	private LinkedList<ApiBody> getResponseApiBodyByPageForm(Class<?> clazz) {
		LinkedList<ApiBody> apiBodies = new LinkedList<ApiBody>();
		for (Field field : clazz.getDeclaredFields()) {
			ModelComment modelComment = field.getAnnotation(ModelComment.class);
			if (modelComment != null) {
				ApiBody docParameter = new ApiBody();
				docParameter.setExplain(modelComment.value());
				docParameter.setName(field.getName());
				docParameter.setType(field.getType().getSimpleName());
				apiBodies.add(docParameter);
			}
		}
		return apiBodies;

	}

	private static LinkedList<ApiBody> getResponseApiBody(Class<?> clazz, List<SerializedField> fields)
			throws Exception {
		LinkedList<ApiBody> docParameters = new LinkedList<ApiBody>();
		Set<String> includes = new HashSet<String>();
		Set<String> excludes = new HashSet<String>();
		for (SerializedField serializedField : fields) {
			if (serializedField.resultClass() == clazz) {
				for (String string : serializedField.excludes()) {
					excludes.add(string);
				}
				for (String string : serializedField.includes()) {
					includes.add(string);
				}
			}
		}

		for (Field field : clazz.getDeclaredFields()) {
			if (includes.size() > 0 || excludes.size() > 0) {
				if (excludes.size() > 0 && excludes.contains(field.getName())) {
					continue;
				} else if (includes.size() > 0 && !includes.contains(field.getName())) {
					continue;
				}
			}
			ModelComment modelComment = field.getAnnotation(ModelComment.class);
			if (modelComment != null) {
				ApiBody docParameter = new ApiBody();
				docParameter.setExplain(modelComment.value());
				docParameter.setName(field.getName());
				docParameter.setType(field.getType().getSimpleName());
				Enumeration enumeration = field.getAnnotation(Enumeration.class);
				if (enumeration != null) {
					docParameter.setExplain(modelComment.value() + "(字典Code=" + enumeration.value() + ")");
				}
				if (!isWrapClass(field.getType())) {
					docParameter.setChildren(getResponseApiBody(field.getType(), fields));
				}
				docParameters.add(docParameter);
			}
		}
		return docParameters;
	}

	private LinkedList<ApiBody> getRequestApiBody(Class<?> class1) {
		LinkedList<ApiBody> docParameters = new LinkedList<ApiBody>();
		for (Field field : class1.getDeclaredFields()) {
			ModelComment modelComment = field.getAnnotation(ModelComment.class);
			if (modelComment != null) {
				ApiBody docParameter = new ApiBody();
				docParameter.setName(field.getName());
				docParameter.setType(field.getType().getSimpleName());
				Enumeration enumeration = field.getAnnotation(Enumeration.class);
				if (enumeration != null) {
					docParameter.setExplain(modelComment.value() + "(字典Code=" + enumeration.value() + ")");
				} else {
					docParameter.setExplain(modelComment.value());
				}
				if (field.getAnnotation(Required.class) != null) {
					docParameter.setRequired("是");
				} else if (field.getAnnotation(Email.class) != null) {
					docParameter.setRequired("是(邮箱)");
				} else if (field.getAnnotation(MobilePhone.class) != null) {
					docParameter.setRequired("是(手机号)");
				} else if (field.getAnnotation(Length.class) != null) {
					if (field.getAnnotation(Length.class).length() != -1) {
						docParameter.setRequired("Y(规定长度：" + field.getAnnotation(Length.class).length() + ")");
					} else {
						docParameter.setRequired("Y(长度范围：" + field.getAnnotation(Length.class).min() + "-"
								+ field.getAnnotation(Length.class).max() + ")");
					}
				} else {
					docParameter.setRequired("否");
				}
				if (!isWrapClass(field.getType())) {
					docParameter.setChildren(getRequestApiBody(field.getType()));
				}
				docParameters.add(docParameter);
			}
		}
		return docParameters;
	}

	/**
	 * 过滤掉不是org.misalen包的
	 * 
	 * @param map
	 * @return
	 */
	private static Map<String, Object> filter(Map<String, Object> map) {
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			if (entry.getValue().getClass().getAnnotation(ControllerApi.class).name().lastIndexOf("/api/") != 1) {
				it.remove(); // OK
			}
		}
		return map;
	}

	public static boolean isWrapClass(Class<?> clz) {
		return clz.isPrimitive() || //
				clz == String.class || //
				clz == Date.class || //
				clz == Boolean.class || //
				clz == Character.class || //
				clz == Byte.class || //
				clz == Integer.class || //
				clz == Short.class || //
				clz == Long.class || //
				clz == Double.class || //
				clz == Void.class || //
				clz == Float.class;
	}

}
