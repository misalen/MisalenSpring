package org.misalen.hibernate.tool.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class HibernateToFormTypHelper {

	private static final Map<String, String> PREFERRED_FORMTYPE_FOR_HIBERNATETYPE = new HashMap<String, String>();

	static {
		// PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Byte.class.getSimpleName(),"text");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Short.class.getSimpleName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Integer.class.getSimpleName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Long.class.getSimpleName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Float.class.getSimpleName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Double.class.getSimpleName(), "number");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(BigDecimal.class.getSimpleName(), "number");
		// PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Boolean.class.getSimpleName());
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Character.class.getSimpleName(), "line");
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(String.class.getSimpleName(), "line");
		// PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Byte[].class.getSimpleName());
		PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.put(Date.class.getSimpleName(), "date");

	}

	public static String getPreferredFormType(String hibernateType) {
		String result = PREFERRED_FORMTYPE_FOR_HIBERNATETYPE.get(hibernateType);
		if (result == null) {
			result = "text";
		}
		return result;
	}

}
