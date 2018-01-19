package org.misalen.poi.importe;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.ObjectUtils;
import org.misalen.poi.base.TemplateBase;

/**
 * 模板导入类
 * 
 * @author DO·VIS
 *
 *         2018年1月19日
 */
public class TemplateImportUtil extends TemplateBase {

	public <T> List<T> parsing(TemplateImportParams importParams, InputStream is, Class<T> clazz) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		bulidParams(importParams, clazz);
		Map<String, String> map = escape(clazz);
		XSSFSheet sheet = workbook.getSheet(importParams.getSheetName());
		int rowNum = sheet.getLastRowNum();
		XSSFRow row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
//		// 正文内容应该从第二行开始,第一行为表头的标题
//		for (int i = 1; i <= rowNum; i++) {
//			row = sheet.getRow(i);
//			int j = 0;
//			Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
//			while (j < colNum) {
//				Object obj = getCellFormatValue(row.getCell(j));
//				cellValue.put(j, obj);
//				j++;
//			}
//			content.put(i, cellValue);
//		}
		workbook.close();
		return null;
	}

	private void bulidParams(TemplateImportParams importParams, Class<?> clazz) {
		super.bulidParams(importParams, clazz);
	}

	private Map<String, String> escape(Class<?> clazz) {
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				ModelComment comment = field.getAnnotation(ModelComment.class);
				System.err.println(field.getName());
				map.put(comment.value(), field.getName());
			}
		}
		return map;
	}
}
