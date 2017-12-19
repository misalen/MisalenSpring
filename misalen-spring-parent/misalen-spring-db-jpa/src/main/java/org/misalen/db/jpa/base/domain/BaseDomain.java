package org.misalen.db.jpa.base.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.misalen.common.annotations.ModelComment;

/**
 * 
 * Sql数据库 Model基础类
 * 
 * @author guochao
 *
 */
@MappedSuperclass
public class BaseDomain extends MysqlUUID {

	@ModelComment("添加时间")
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date addTime;

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
