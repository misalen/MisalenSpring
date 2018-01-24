package org.misalen.poi.importe;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;
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

	public <T> List<T> parsing(InputStream is, Class<T> clazz) throws Exception {
		return parsing(new TemplateImportParams(), is, clazz, null);
	}

	public <T> List<T> parsing(TemplateImportParams importParams, InputStream is, Class<T> clazz) throws Exception {
		return parsing(importParams, is, clazz, null);
	}

	public <T> List<T> parsing(TemplateImportParams importParams, InputStream is, Class<T> clazz,
			Map<String, String> escapeMap) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		bulidParams(importParams, clazz);
		Map<String, String> map = escape(clazz);
		if (escapeMap != null) {
			map.putAll(escapeMap);
		}
		XSSFSheet sheet = workbook.getSheet(importParams.getSheetName());
		XSSFRow row = sheet.getRow(importParams.getTitleLine() + 1);
		int colNum = row.getPhysicalNumberOfCells();
		int index = importParams.getTitleLine() + 2;
		List<T> list = new LinkedList<T>();
		for (Cell cell : row) {
			XSSFRow valueRow = sheet.getRow(index);
			T t = clazz.newInstance();
			for (int i = 0; i < colNum; i++) {
				System.err.println(getValue(valueRow.getCell(i)));
			}
			index++;
		}
		workbook.close();
		return null;
	}

	public String getValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", LocaleUtil.getUserLocale());
				sdf.setTimeZone(LocaleUtil.getUserTimeZone());
				return sdf.format(cell.getDateCellValue());
			}
			return Double.toString(cell.getNumericCellValue());
		case STRING:
			return cell.getRichStringCellValue().toString();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		case BOOLEAN:
			return cell.getBooleanCellValue() ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
		case ERROR:
			return ErrorEval.getText(cell.getErrorCellValue());
		default:
			return "Unknown Cell Type: " + cell.getCellTypeEnum();
		}
	}

	private void bulidParams(TemplateImportParams importParams, Class<?> clazz) {
		super.bulidParams(importParams, clazz);
	}

	private Map<String, String> escape(Class<?> clazz) {
		Map<String, String> map = new HashMap<String, String>();
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				ModelComment comment = field.getAnnotation(ModelComment.class);
				map.put(comment.value(), field.getName());
			}
		}
		return map;
	}
}
