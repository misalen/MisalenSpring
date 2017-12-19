package org.misalen.db.mybatis.sys.domain;

import java.io.Serializable;

public class City implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String province;

	City() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", province=" + province + "]";
	}
}
