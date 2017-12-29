package org.misalen.common.utils;

import java.util.LinkedList;
import java.util.List;

import org.misalen.common.annotations.ModelComment;

/**
 * 
 * 分页查询返回类
 * 
 * @author guochao
 *
 * @param <T>
 */

public class PageFrom<T> {
	@ModelComment(value = "外层List")
	private List<T> list;
	@ModelComment(value = "页数")
	private Integer page = 1;
	@ModelComment(value = "页宽")
	private Integer rows = 10;
	@ModelComment(value = "总数")
	private Long total = -1l;
	@ModelComment(value = "倒叙字段")
	private LinkedList<String> desc;
	@ModelComment(value = "正序字段")
	private LinkedList<String> asc;
	@ModelComment(value = "检索")
	private List<Retrieval> retrievals;

	/**
	 * 获取retrievals
	 * 
	 * @return retrievals retrievals
	 */
	public List<Retrieval> getRetrievals() {
		return retrievals;
	}

	/**
	 * 设置retrievals
	 * 
	 * @param retrievals
	 *            retrievals
	 */
	public void setRetrievals(List<Retrieval> retrievals) {
		this.retrievals = retrievals;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public LinkedList<String> getDesc() {
		return desc;
	}

	public void addDesc(String desc) {
		if (this.desc == null) {
			this.desc = new LinkedList<String>();
		}
		this.desc.add(desc);
	}

	public void setDesc(LinkedList<String> desc) {
		this.desc = desc;
	}

	public LinkedList<String> getAsc() {
		return asc;
	}

	public void setAsc(LinkedList<String> asc) {
		this.asc = asc;
	}

	public void addAsc(String asc) {
		if (this.asc == null) {
			this.asc = new LinkedList<String>();
		}
		this.asc.add(asc);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public static enum Condition {
		gt, gte, lt, lte, eq, like
	}

	public static class Retrieval {
		private String name;
		private String condition;
		private String value;

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
		 * 获取condition
		 * 
		 * @return condition condition
		 */
		public String getCondition() {
			return condition;
		}

		/**
		 * 设置condition
		 * 
		 * @param condition
		 *            condition
		 */
		public void setCondition(String condition) {
			this.condition = condition;
		}

		/**
		 * 获取value
		 * 
		 * @return value value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 设置value
		 * 
		 * @param value
		 *            value
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}
}
