package org.misalen.poi.importe;

import org.misalen.poi.base.TemplateParamsBase;

/**
 * Excel 模板导出参数
 * 
 * @author DO·VIS
 *
 *         2018年1月18日
 */
public class TemplateImportParams extends TemplateParamsBase {

	/**
	 * 字段数量
	 */
	private int filedSize;
	/**
	 * 表格名称
	 */
	private String title;

	/**
	 * 名称高度
	 */
	private int titleHeight = 30;

	/**
	 * 列宽
	 */
	private int columnWidth = 5000;

	/**
	 * 获取字段数量
	 * 
	 * @return filedSize 字段数量
	 */
	public int getFiledSize() {
		return filedSize;
	}

	/**
	 * 设置字段数量
	 * 
	 * @param filedSize
	 *            字段数量
	 */
	public void setFiledSize(int filedSize) {
		this.filedSize = filedSize;
	}

	/**
	 * 获取表格名称
	 * 
	 * @return title 表格名称
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置表格名称
	 * 
	 * @param title
	 *            表格名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取名称高度
	 * 
	 * @return titleHeight 名称高度
	 */
	public int getTitleHeight() {
		return titleHeight;
	}

	/**
	 * 设置名称高度
	 * 
	 * @param titleHeight
	 *            名称高度
	 */
	public void setTitleHeight(int titleHeight) {
		this.titleHeight = titleHeight;
	}

	/**
	 * 获取列宽
	 * 
	 * @return columnWidth 列宽
	 */
	public int getColumnWidth() {
		return columnWidth;
	}

	/**
	 * 设置列宽
	 * 
	 * @param columnWidth
	 *            列宽
	 */
	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

}
