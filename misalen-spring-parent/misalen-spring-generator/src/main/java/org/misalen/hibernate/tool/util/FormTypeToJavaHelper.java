package org.misalen.hibernate.tool.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class FormTypeToJavaHelper {

	private static final Map<String, String> PREFERRED_JAVATYPE_FOR_FORMTYPE = new HashMap<String, String>();

	static {
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("line", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("password", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("lines", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("date", Date.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("datetime", Date.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("number", Integer.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("image", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("code", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("file", String.class.getSimpleName());
	}

	public static String getPreferredFormType(String elementId, Integer scale, String code) {
		String result = PREFERRED_JAVATYPE_FOR_FORMTYPE.get(elementId);
		if (result == null) {
			result = String.class.getSimpleName();
		} else if (result!=null&&result.equals(Integer.class.getSimpleName())) {
			if (scale > 0) {
				result = BigDecimal.class.getSimpleName();
			}
		} else if (elementId.equals("code")) {
			if (code != null && code.equals("yesorno")) {
				result = Boolean.class.getSimpleName();
			}
		}
		return result;
	}

}
