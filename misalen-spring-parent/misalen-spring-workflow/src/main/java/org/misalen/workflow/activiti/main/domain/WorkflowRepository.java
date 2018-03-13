package org.misalen.workflow.activiti.main.domain;

public class WorkflowRepository {

	private String name;
	private String description;
	private Integer revision;
	private String key = "process";

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
	 * 获取description
	 * 
	 * @return description description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置description
	 * 
	 * @param description
	 *            description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取revision
	 * 
	 * @return revision revision
	 */
	public Integer getRevision() {
		return revision;
	}

	/**
	 * 设置revision
	 * 
	 * @param revision
	 *            revision
	 */
	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	/**
	 * 获取key
	 * 
	 * @return key key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 *            key
	 */
	public void setKey(String key) {
		this.key = key;
	}

}