package org.misalen.common.global;

import java.util.ArrayList;
import java.util.List;

public class ModuleUtil {

	public final static String packageName = "org.misalen";
	private final static String branchName = "branch";

	private final static String[] modulesBranch = new String[] { "WebModuleCode", "DbJpaModuleCode" };

	public static String[] getPropertiesName() {
		List<String> list = new ArrayList<String>();
		for (String string : modulesBranch) {
			try {
				String simpleName = packageName + "." + branchName + "." + string;
				Class<?> clazz = Class.forName(simpleName);
				if (null != clazz) {
					String name = ((ModuleCode) clazz.newInstance()).getName();
					name = name.replace("-spring", "");
					list.add(name);
				}
			} catch (Exception t) {

			}
		}
		return list.toArray(new String[] {});
	}
}
