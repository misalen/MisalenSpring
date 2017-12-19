package org.misalen.hibernate.tool.util;

public class FiledData {

	private Named name;
	private String comment;
	private String filedType;
	private Boolean nullable;
	private Integer length;
	private String enumerationCode;

	public String getEnumerationCode() {
		return enumerationCode;
	}

	public void setEnumerationCode(String enumerationCode) {
		this.enumerationCode = enumerationCode;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Named getName() {
		return name;
	}

	public void setName(Named name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

}
