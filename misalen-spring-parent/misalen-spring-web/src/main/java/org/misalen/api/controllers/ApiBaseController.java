package org.misalen.api.controllers;

import org.misalen.api.forms.BasePageForm;
import org.misalen.common.advice.structure.ErrorCode;
import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.utils.PageFrom;

/**
 * BaseController
 * 
 * @author guochao
 *
 */
public class ApiBaseController {

	protected RestResult<?> renderError(ErrorCode msg) {
		return RestResult.error(msg);
	}

	protected RestResult<?> renderSuccess() {
		return RestResult.ok();
	}

	protected <T> RestResult<T> renderSuccess(T obj) {
		return RestResult.ok(obj);
	}

	protected static <T> PageFrom<T> formToPage(BasePageForm form, Class<T> t) {
		PageFrom<T> from = new PageFrom<T>();
		from.setAsc(form.getAsc());
		from.setDesc(form.getDesc());
		from.setRetrievals(form.getRetrievals());
		from.setPage(form.getPage());
		from.setRows(form.getRows());
		return from;
	}
}
