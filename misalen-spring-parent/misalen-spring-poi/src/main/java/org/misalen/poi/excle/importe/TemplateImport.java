package org.misalen.poi.excle.importe;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.misalen.common.annotations.Enumeration;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.utils.ObjectUtils;
import org.misalen.poi.excle.base.TemplateBase;
import org.misalen.web.domain.SysDictionaryData;
import org.misalen.web.repository.SysDictionaryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 模板导入类
 * 
 * @author DO·VIS
 *
 *         2018年1月19日
 */
@Component
public class TemplateImport extends TemplateBase {
	@Autowired
	SysDictionaryDataRepository dataRepository;

	public <T> List<T> parsing(InputStream is, Class<T> clazz) throws Exception {
		return parsing(new TemplateImportParams(), is, clazz);
	}

	public <T> List<T> parsing(TemplateImportParams importParams, InputStream is, Class<T> clazz) throws Exception {
		return parsing(importParams, is, clazz, null);
	}

	public <T> List<T> parsing(TemplateImportParams importParams, InputStream is, Class<T> clazz,
			Map<String, FieldsThat> escapeMap) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		bulidParams(importParams, clazz);
		Map<String, FieldsThat> map = escape(clazz);
		if (escapeMap != null) {
			map.putAll(escapeMap);
		}
		XSSFSheet sheet = workbook.getSheet(importParams.getSheetName());
		XSSFRow row = sheet.getRow(importParams.getTitleLine() + 1);
		int colNum = row.getPhysicalNumberOfCells();
		int rowNum = sheet.getLastRowNum();

		List<T> list = new LinkedList<T>();

		for (int i = importParams.getTitleLine() + 2; i < rowNum + 1; i++) {
			T t = clazz.newInstance();
			XSSFRow valueRow = sheet.getRow(i);
			for (int j = 0; j < colNum; j++) {
				changeType(t, map.get(getValue(row.getCell(j))), getValue(valueRow.getCell(j)));
			}
			list.add(t);
		}
		workbook.close();
		return list;
	}

	private <T> void changeType(T t, FieldsThat fieldsThat, Object value) throws Exception {
		Field f = t.getClass().getDeclaredField(fieldsThat.name);
		f.setAccessible(true);
		if (fieldsThat.type.isAssignableFrom(String.class)) {
			if (fieldsThat.enumeration) {
				f.set(t, fieldsThat.enumerationCode.get(value.toString()));
			} else if (value.getClass().isAssignableFrom(Double.class)) {
				f.set(t, substring(value.toString()));
			} else {
				f.set(t, value.toString());
			}
		} else if (fieldsThat.type.isAssignableFrom(Double.class)) {
			f.set(t, Double.valueOf(value.toString()));
		} else if (fieldsThat.type.isAssignableFrom(Integer.class)) {
			f.set(t, Integer.valueOf(substring(value.toString())));
		} else if (fieldsThat.type.isAssignableFrom(Long.class)) {
			f.set(t, Long.valueOf(substring(value.toString())));
		} else if (fieldsThat.type.isAssignableFrom(Date.class)) {
			f.set(t, value);
		}
	}

	private String substring(String string) {
		string = string.substring(0, string.indexOf("."));
		return string;
	}

	@SuppressWarnings("deprecation")
	private Object getValue(Cell cell) {
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				DateFormat sdf = new SimpleDateFormat(DATE_FORMAT, LocaleUtil.getUserLocale());
				sdf.setTimeZone(LocaleUtil.getUserTimeZone());
				return cell.getDateCellValue();
			}
			return cell.getNumericCellValue();
		case STRING:
			return cell.getRichStringCellValue().toString();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		case BOOLEAN:
			return cell.getBooleanCellValue() ? Boolean.TRUE : Boolean.FALSE;
		case ERROR:
			return ErrorEval.getText(cell.getErrorCellValue());
		default:
			return "Unknown Cell Type: " + cell.getCellTypeEnum();
		}
	}

	private void bulidParams(TemplateImportParams importParams, Class<?> clazz) {
		super.bulidParams(importParams, clazz);
	}

	private Map<String, FieldsThat> escape(Class<?> clazz) {
		Map<String, FieldsThat> map = new HashMap<String, FieldsThat>();
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				ModelComment comment = field.getAnnotation(ModelComment.class);
				FieldsThat fieldsThat = new FieldsThat();
				fieldsThat.name = field.getName();
				fieldsThat.type = field.getType();

				Enumeration code = field.getAnnotation(Enumeration.class);
				if (code != null) {
					fieldsThat.enumeration = true;
					List<SysDictionaryData> datas = dataRepository.findByDtCode(code.value());
					Map<String, String> enumMap = new HashMap<String, String>();
					for (SysDictionaryData sysDictionaryData : datas) {
						enumMap.put(sysDictionaryData.getName(), sysDictionaryData.getCode());
					}
					fieldsThat.enumerationCode = enumMap;
				}

				map.put(comment.value(), fieldsThat);
			}
		}
		return map;
	}

	public class FieldsThat {
		public String name;
		public boolean enumeration;
		public Map<String, String> enumerationCode;
		public Class<?> type = String.class;
	}
}
