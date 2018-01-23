package org.misalen.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ApiBody
 * @author: misalen
 * @date: 2016年7月29日 下午2:50:15
 * 
 * @Desc: 接口参数
 *
 */
public final class ApiBody {
	private String name;
	private String explain;
	private String required;
	private String type;
	private List<ApiBody> children = new ArrayList<>();

	/**
	 * 获取name
	 * 
	 * @return name name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 获取required
	 * 
	 * @return required required
	 */
	public String getRequired() {
		return required;
	}

	/**
	 * 设置required
	 * 
	 * @param required
	 *            required
	 */
	public void setRequired(String required) {
		this.required = required;
	}

	/**
	 * 获取type
	 * 
	 * @return type type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type
	 * 
	 * @param type
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取children
	 * 
	 * @return children children
	 */
	public List<ApiBody> getChildren() {
		return children;
	}

	/**
	 * 设置children
	 * 
	 * @param children
	 *            children
	 */
	public void setChildren(List<ApiBody> children) {
		this.children = children;
	}

}
