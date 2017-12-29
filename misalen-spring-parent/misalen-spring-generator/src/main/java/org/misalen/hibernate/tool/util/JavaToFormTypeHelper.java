package org.misalen.hibernate.tool.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JavaToFormTypeHelper {

	private static final Map<String, String> PREFERRED_FORMTYPE_FOR_HIBERNATETYPE = new HashMap<String, String>();

	static {
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Short.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Integer.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Long.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Float.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Double.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(BigDecimal.class.getName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Boolean.class.getName(), "code");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Character.class.getName(), "line");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(String.class.getName(), "line");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Date.class.getName(), "date");

	}

	public static String getPreferredFormType(String hibernateType) {
		String result = PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.get(hibernateType);
		if (result == null) {
			result = "line";
		}
		return result;
	}

}
