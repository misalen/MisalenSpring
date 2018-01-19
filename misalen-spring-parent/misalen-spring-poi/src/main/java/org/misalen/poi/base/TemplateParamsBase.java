package org.misalen.poi.base;

public class TemplateParamsBase {
	/**
	 * 标题是第几行
	 */
	private int titleLine = 0;
	/**
	 * 标题是第几行
	 */
	private boolean titleHidden = false;
	
	/**
	 * sheet名称
	 */
	private String sheetName;

	/**  
	 * 获取标题是第几行  
	 * @return titleLine 标题是第几行  
	 */
	public int getTitleLine() {
		return titleLine;
	}
	

	/**  
	 * 设置标题是第几行  
	 * @param titleLine 标题是第几行  
	 */
	public void setTitleLine(int titleLine) {
		this.titleLine = titleLine;
	}
	

	/**  
	 * 获取标题是第几行  
	 * @return titleHidden 标题是第几行  
	 */
	public boolean isTitleHidden() {
		return titleHidden;
	}
	

	/**  
	 * 设置标题是第几行  
	 * @param titleHidden 标题是第几行  
	 */
	public void setTitleHidden(boolean titleHidden) {
		this.titleHidden = titleHidden;
	}
	



	/**  
	 * 获取sheet名称  
	 * @return sheetName sheet名称  
	 */
	public String getSheetName() {
		return sheetName;
	}
	

	/**  
	 * 设置sheet名称  
	 * @param sheetName sheet名称  
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	
	
}
