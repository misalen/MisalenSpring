package org.misalen.generator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysFlowNodeAtta extends BaseDomain {

	@ModelComment("节点Id")
	@Column(length = 255, nullable = false)
	private String nodeId;

	@ModelComment("连接节点Id")
	@Column(length = 255, nullable = false)
	private String targetId;

	@ModelComment("名称")
	@Column(length = 255, nullable = false)
	private String nodeAttaName;

	/**
	 * 获取targetId
	 * 
	 * @return targetId targetId
	 */
	public String getTargetId() {
		return targetId;
	}

	/**
	 * 设置targetId
	 * 
	 * @param targetId
	 *            targetId
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	/**
	 * 获取nodeId
	 * 
	 * @return nodeId nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * 设置nodeId
	 * 
	 * @param nodeId
	 *            nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 获取nodeAttaName
	 * 
	 * @return nodeAttaName nodeAttaName
	 */
	public String getNodeAttaName() {
		return nodeAttaName;
	}

	/**
	 * 设置nodeAttaName
	 * 
	 * @param nodeAttaName
	 *            nodeAttaName
	 */
	public void setNodeAttaName(String nodeAttaName) {
		this.nodeAttaName = nodeAttaName;
	}

}
