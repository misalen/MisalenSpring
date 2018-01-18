/**
 * Copyright 2013-2015 JueYue (qrb.jueyue@gmail.com)
 *   
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.misalen.poi;

/**
 * Excel 导出参数
 * 
 * @author DO·VIS
 *
 *         2018年1月18日
 */
public class TemplateExportParams {
	/**
	 * 标题是第几行
	 */
	public int titleLine = 0;
	/**
	 * 字段数量
	 */
	public int filedSize;
	/**
	 * 表格名称
	 */
	private String title;

	/**
	 * 名称高度
	 */
	private int titleHeight = 30;

	/**
	 * 第二行高度
	 */
	private int secondHeight = 20;
	/**
	 * sheet名称
	 */
	private String sheetName;

	public TemplateExportParams() {

	}

	/**
	 * 获取标题是第几行
	 * 
	 * @return titleLine 标题是第几行
	 */
	public int getTitleLine() {
		return titleLine;
	}

	/**
	 * 设置标题是第几行
	 * 
	 * @param titleLine
	 *            标题是第几行
	 */
	public void setTitleLine(int titleLine) {
		this.titleLine = titleLine;
	}

	public TemplateExportParams(String title, String sheetName) {
		this.title = title;
		this.sheetName = sheetName;
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
	 * 获取第二行高度  
	 * @return secondHeight 第二行高度  
	 */
	public int getSecondHeight() {
		return secondHeight;
	}
	

	/**  
	 * 设置第二行高度  
	 * @param secondHeight 第二行高度  
	 */
	public void setSecondHeight(int secondHeight) {
		this.secondHeight = secondHeight;
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
	 * 获取sheet名称
	 * 
	 * @return sheetName sheet名称
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * 设置sheet名称
	 * 
	 * @param sheetName
	 *            sheet名称
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

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

}
