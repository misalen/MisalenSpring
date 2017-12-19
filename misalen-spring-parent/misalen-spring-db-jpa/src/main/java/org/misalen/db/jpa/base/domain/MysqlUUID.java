package org.misalen.db.jpa.base.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.misalen.common.annotations.ModelComment;

/**
 * Mysql数据库的主键生成定义:系统自动生成32位不同的字符序列
 * 
 * @author Jeff Xu
 * @since 2015-12-09
 */
@MappedSuperclass
public class MysqlUUID {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@ModelComment("主键")
	@Column(length = 32, nullable = true)
	protected String primaryKey;
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}


}
