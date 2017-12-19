package org.misalen.api;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClazzUtil {
	private static final String TYPE_NAME_PREFIX = "class ";

	public static Object newInstance(Type type)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = getClass(type);
		if (clazz == null) {
			return null;
		}
		return clazz.newInstance();
	}

	public static String getClassName(Type type) {
		if (type == null) {
			return "";
		}
		String className = type.toString();
		if (className.startsWith(TYPE_NAME_PREFIX)) {
			className = className.substring(TYPE_NAME_PREFIX.length());
		}
		return className;
	}

	public static Class<?> getClass(Type type) throws ClassNotFoundException {
		String className = getClassName(type);
		if (className == null || className.isEmpty()) {
			return null;
		}
		return Class.forName(className);
	}

	public static Type[] getParameterizedTypes(Type superclassType) {
		if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
			return null;
		}
		return ((ParameterizedType) superclassType).getActualTypeArguments();
	}
}
