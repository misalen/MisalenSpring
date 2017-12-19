package org.misalen.generator.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

@Entity
@Table
public class SysFlowNode extends BaseDomain {
	
	@ModelComment("流程Id")
	@Column( length = 255, nullable = false)
	private String flowId;
	@ModelComment("名称")
	@Column( length = 255, nullable = false)
	private String nodeName;
	@ModelComment("节点类型")
	@Column( length = 255, nullable = false)
	private String nodeType;
	@ModelComment("节点样式")
	@Column( length = 255, nullable = false)
	private String style;
	@ModelComment("绑定的表单地址")
	@Column( length = 255)
	private String bindForm;

	@OneToMany(targetEntity = SysFlowNodeAtta.class, cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "NODE_ID", updatable = false)
	@ModelComment("与字典数据的关系")
	private Set<SysFlowNodeAtta> sysFlowNodeAttas;

	/**
	 * 获取sysFlowNodeAttas
	 * 
	 * @return sysFlowNodeAttas sysFlowNodeAttas
	 */
	public Set<SysFlowNodeAtta> getSysFlowNodeAttas() {
		return sysFlowNodeAttas;
	}

	/**
	 * 设置sysFlowNodeAttas
	 * 
	 * @param sysFlowNodeAttas
	 *            sysFlowNodeAttas
	 */
	public void setSysFlowNodeAttas(Set<SysFlowNodeAtta> sysFlowNodeAttas) {
		this.sysFlowNodeAttas = sysFlowNodeAttas;
	}

	/**
	 * 获取flowId
	 * 
	 * @return flowId flowId
	 */
	public String getFlowId() {
		return flowId;
	}

	/**
	 * 设置flowId
	 * 
	 * @param flowId
	 *            flowId
	 */
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	/**
	 * 获取nodeName
	 * 
	 * @return nodeName nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * 设置nodeName
	 * 
	 * @param nodeName
	 *            nodeName
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * 获取nodeType
	 * 
	 * @return nodeType nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}

	/**
	 * 设置nodeType
	 * 
	 * @param nodeType
	 *            nodeType
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * 获取style
	 * 
	 * @return style style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 设置style
	 * 
	 * @param style
	 *            style
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 获取bindForm
	 * 
	 * @return bindForm bindForm
	 */
	public String getBindForm() {
		return bindForm;
	}

	/**
	 * 设置bindForm
	 * 
	 * @param bindForm
	 *            bindForm
	 */
	public void setBindForm(String bindForm) {
		this.bindForm = bindForm;
	}

}
