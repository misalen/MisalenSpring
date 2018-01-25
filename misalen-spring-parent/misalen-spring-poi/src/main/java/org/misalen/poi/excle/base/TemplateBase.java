package org.misalen.poi.excle.base;

import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.TextUtil;

public class TemplateBase {
	protected static final String DATE_FORMAT="yyyy-MM-dd";
	/**
	 * 设置基本参数
	 * 
	 * @param exportParams
	 * @param clazz
	 */
	protected void bulidParams(TemplateParamsBase exportParams, Class<?> clazz) {
		ModelComment comment = null;
		if (exportParams.isTitleHidden()) {
			exportParams.setTitleLine(-1);
		}
		if (TextUtil.isNullOrEmpty(exportParams.getSheetName())) {
			if (comment == null) {
				comment = clazz.getAnnotation(ModelComment.class);
			}
			if (comment != null) {
				exportParams.setSheetName(comment.value());
			}
		}
	}

}
