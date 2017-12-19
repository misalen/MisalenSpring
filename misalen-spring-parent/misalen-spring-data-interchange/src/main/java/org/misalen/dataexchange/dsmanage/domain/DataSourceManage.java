package org.misalen.dataexchange.dsmanage.domain;

import java.util.Set;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.misalen.common.annotations.ModelComment;
import org.misalen.db.jpa.base.domain.BaseDomain;

/**
 * 
 * @author DO.VIS
 *
 */
@Entity
@Table
public class DataSourceManage extends BaseDomain {

	@ModelComment("数据源名称")
	@Column(nullable = true)
	private String dsname;
	

	@ModelComment("类型")
	@Column(nullable = false)
	private String leixing;
	

	@ModelComment("IP地址")
	@Column(nullable = true)
	private String iPdizhi;
	

	@ModelComment("端口")
	@Column(nullable = true)
	private Integer port;
	

	@ModelComment("用户名")
	@Column(nullable = true)
	private String username;
	

	@ModelComment("密码")
	@Column(nullable = false)
	private String password;
	
	

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}


	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}


	public String getIPdizhi() {
		return iPdizhi;
	}

	public void setIPdizhi(String iPdizhi) {
		this.iPdizhi = iPdizhi;
	}


	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
