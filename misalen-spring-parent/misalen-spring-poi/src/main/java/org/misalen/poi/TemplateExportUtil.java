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

import java.lang.reflect.Field;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.ObjectUtils;
import org.misalen.common.utils.TextUtil;

public class TemplateExportUtil {

	public static XSSFWorkbook export(TemplateExportParams exportParams, Class<?> clazz) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(exportParams.getSheetName());
		bulidParams(exportParams, clazz);
		setElucidation(sheet, exportParams, clazz);
		setTitle(sheet, exportParams);
		return workbook;
	}

	/**
	 * 设置基本参数
	 * 
	 * @param exportParams
	 * @param clazz
	 */
	static void bulidParams(TemplateExportParams exportParams, Class<?> clazz) {
		ModelComment comment = null;
		if (TextUtil.isNullOrEmpty(exportParams.getTitle())) {
			comment = clazz.getAnnotation(ModelComment.class);
			if (comment != null) {
				exportParams.setTitle(comment.value());
			}
		}
		if (TextUtil.isNullOrEmpty(exportParams.getSheetName())) {
			if (comment == null) {
				comment = clazz.getAnnotation(ModelComment.class);
			}
			if (comment != null) {
				exportParams.setSheetName(comment.value());
			}
		}
		int filedSize = 0;
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				filedSize++;
			}
		}
		if (filedSize == 0) {
			filedSize = 1;
		}
		exportParams.setFiledSize(filedSize);
	}

	/**
	 * 设置字段属性说明
	 * 
	 * @param sheet
	 * @param exportParams
	 * @param clazz
	 */
	static void setElucidation(XSSFSheet sheet, TemplateExportParams exportParams, Class<?> clazz) {
		int line = exportParams.getTitleLine() + 1;
		XSSFRow elucidationRow = sheet.createRow(line);
		elucidationRow.setHeightInPoints(exportParams.getSecondHeight());
		int column = 0;
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				ModelComment comment = field.getAnnotation(ModelComment.class);
				XSSFCell elucidationCell = elucidationRow.createCell(column++);
				elucidationCell.setCellValue(comment.value());
				XSSFCellStyle elucidationStyle = elucidationCell.getCellStyle();
				elucidationStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
				elucidationStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			}
		}
	}

	/**
	 * 设置标题
	 * 
	 * @param sheet
	 * @param exportParams
	 */
	static void setTitle(XSSFSheet sheet, TemplateExportParams exportParams) {
		// // 冻结标题行
		// sheet.createFreezePane(0, exportParams.getTitleLine() + 1);
		// 标题高度
		XSSFRow titleRow = sheet.createRow(exportParams.getTitleLine());
		titleRow.setHeightInPoints(exportParams.getTitleHeight());
		// 标题
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(exportParams.getTitle());

		// 标题居中
		XSSFCellStyle titleStyle = titleCell.getCellStyle();
//		titleStyle.setAlignment(HorizontalAlignment.RIGHT);
		//titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		// 合并标题
		CellRangeAddress cra = new CellRangeAddress(exportParams.getTitleLine(), exportParams.getTitleLine(), 0,
				exportParams.getFiledSize() - 1);
		sheet.addMergedRegion(cra);
	}

}
