package org.misalen.poi.base;

import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.TextUtil;

public class TemplateBase {
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
