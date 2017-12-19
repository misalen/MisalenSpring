package org.misalen.hibernate.tool.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class FormTypToJavaHelper {

	private static final Map<String, String> PREFERRED_JAVATYPE_FOR_FORMTYPE = new HashMap<String, String>();

	static {
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("number", Integer.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("line", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("lines", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("image", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("select", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("textarea", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("file", String.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("date", Date.class.getSimpleName());
		PREFERRED_JAVATYPE_FOR_FORMTYPE.put("datetime", Date.class.getSimpleName());
	}

	public static String getPreferredFormType(String formType) {
		String result = PREFERRED_JAVATYPE_FOR_FORMTYPE.get(formType);
		if (result == null) {
			result = String.class.getSimpleName();
		}
		return result;
	}

}
