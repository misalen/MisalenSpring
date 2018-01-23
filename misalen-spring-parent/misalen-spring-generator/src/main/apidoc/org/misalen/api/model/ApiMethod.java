package org.misalen.api.model;

import java.util.LinkedList;

/**
 * 
 * 
 * @ClassName: ApiMethod
 * @author: misalen
 * @date: 2016年7月29日 下午2:16:41
 * 
 * @Desc: 文档类 每个类对应一个方法
 *
 */
public final class ApiMethod {

	private Integer sort;
	private String explain;
	private String url;
	private String method;
	private LinkedList<ApiBody> requestBody = new LinkedList<ApiBody>();
	private LinkedList<ApiBody> responseBody = new LinkedList<ApiBody>();

	/**
	 * 获取method
	 * 
	 * @return method method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 设置method
	 * 
	 * @param method
	 *            method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 获取sort
	 * 
	 * @return sort sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置sort
	 * 
	 * @param sort
	 *            sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取explain
	 * 
	 * @return explain explain
	 */
	public String getExplain() {
		return explain;
	}

	/**
	 * 设置explain
	 * 
	 * @param explain
	 *            explain
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}

	/**
	 * 获取url
	 * 
	 * @return url url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url
	 * 
	 * @param url
	 *            url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取requestBody
	 * 
	 * @return requestBody requestBody
	 */
	public LinkedList<ApiBody> getRequestBody() {
		return requestBody;
	}

	/**
	 * 设置requestBody
	 * 
	 * @param requestBody
	 *            requestBody
	 */
	public void setRequestBody(LinkedList<ApiBody> requestBody) {
		this.requestBody = requestBody;
	}

	/**
	 * 获取responseBody
	 * 
	 * @return responseBody responseBody
	 */
	public LinkedList<ApiBody> getResponseBody() {
		return responseBody;
	}

	/**
	 * 设置responseBody
	 * 
	 * @param responseBody
	 *            responseBody
	 */
	public void setResponseBody(LinkedList<ApiBody> responseBody) {
		this.responseBody = responseBody;
	}

}
