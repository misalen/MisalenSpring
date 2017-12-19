package org.misalen.api.forms;

import java.util.LinkedList;
import java.util.List;

import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.PageFrom;

public class BasePageForm {
	
	@ModelComment(value = "页数")
	private Integer page = 1;
	@ModelComment(value = "页宽")
	private Integer rows = 10;
	@ModelComment(value = "倒叙字段")
	private LinkedList<String> desc;
	@ModelComment(value = "正序字段")
	private LinkedList<String> asc;
	@ModelComment(value = "检索")
	private List<PageFrom.Retrieval> retrievals;
	
	public List<PageFrom.Retrieval> getRetrievals() {
		return retrievals;
	}
	
	public void setRetrievals(List<PageFrom.Retrieval> retrievals) {
		this.retrievals = retrievals;
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
	public LinkedList<String> getDesc() {
		return desc;
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

}
