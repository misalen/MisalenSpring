package org.misalen.api.model;

import java.util.LinkedList;

/**
 * 
 * @ClassName: ApiController
 * @author: misalen
 * @date: 2016年7月29日 下午2:17:20
 * 
 * @Desc:文档接口类 每个类对应一个接口
 *
 */
public final class ApiController {
	private String name;
	private String explain;
	private Integer sort;
	private LinkedList<ApiMethod> docInterfaces = new LinkedList<ApiMethod>();
	/**  
	 * 获取name  
	 * @return name name  
	 */
	public String getName() {
		return name;
	}
	
	/**  
	 * 设置name  
	 * @param name name  
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**  
	 * 获取explain  
	 * @return explain explain  
	 */
	public String getExplain() {
		return explain;
	}
	
	/**  
	 * 设置explain  
	 * @param explain explain  
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
	/**  
	 * 获取sort  
	 * @return sort sort  
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**  
	 * 设置sort  
	 * @param sort sort  
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/**  
	 * 获取docInterfaces  
	 * @return docInterfaces docInterfaces  
	 */
	public LinkedList<ApiMethod> getDocInterfaces() {
		return docInterfaces;
	}
	
	/**  
	 * 设置docInterfaces  
	 * @param docInterfaces docInterfaces  
	 */
	public void setDocInterfaces(LinkedList<ApiMethod> docInterfaces) {
		this.docInterfaces = docInterfaces;
	}
	
	
	
}