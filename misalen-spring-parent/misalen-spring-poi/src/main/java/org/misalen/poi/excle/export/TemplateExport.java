package org.misalen.poi.excle.export;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
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
 * 模板导出类
 * 
 * @author DO·VIS
 *
 *         2018年1月19日
 */
@Component
public class TemplateExport extends TemplateBase {
	@Autowired
	SysDictionaryDataRepository dataRepository;

	public XSSFWorkbook export(TemplateExportParams exportParams, Class<?> clazz) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		bulidParams(exportParams, clazz);
		XSSFSheet sheet = workbook.createSheet(exportParams.getSheetName());
		setElucidation(sheet, exportParams, clazz, workbook);
		if (!exportParams.isTitleHidden()) {
			setTitle(sheet, exportParams, workbook, getFiledSize(clazz));
		}
		return workbook;
	}

	/**
	 * 设置基本参数
	 * 
	 * @param exportParams
	 * @param clazz
	 */
	void bulidParams(TemplateExportParams exportParams, Class<?> clazz) {
		super.bulidParams(exportParams, clazz);

	}

	private int getFiledSize(Class<?> clazz) {
		int filedSize = 0;
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				filedSize++;
			}
		}
		if (filedSize == 0) {
			filedSize = 1;
		}
		return filedSize;
	}

	/**
	 * 设置字段属性说明
	 * 
	 * @param sheet
	 * @param exportParams
	 * @param clazz
	 * @param workbook
	 */
	void setElucidation(XSSFSheet sheet, TemplateExportParams exportParams, Class<?> clazz, XSSFWorkbook workbook) {
		int line = exportParams.getTitleLine() + 1;
		// 冻结行
		sheet.createFreezePane(0, line + 1);
		XSSFRow elucidationRow = sheet.createRow(line);
		int column = 0;
		for (Field field : clazz.getDeclaredFields()) {
			if (ObjectUtils.isBaseDataType(field.getType())) {
				sheet.setColumnWidth(column, exportParams.getColumnWidth());
				setCellFormat(field, workbook, sheet, column, line);
				ModelComment comment = field.getAnnotation(ModelComment.class);
				XSSFCell elucidationCell = elucidationRow.createCell(column);
				elucidationCell.setCellValue(comment.value());
				elucidationCell.setCellStyle(setCellStyle(workbook));
				column++;
			}
		}
	}

	/**
	 * 返回单元格样式
	 * 
	 * @param workbook
	 * @return
	 */
	XSSFCellStyle setCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setWrapText(true);
		return cellStyle;

	}

	/**
	 * 根据类型设置单元格格式
	 * 
	 * @param field
	 * @param workbook
	 * @param sheet
	 * @param column
	 * @param line
	 */
	void setCellFormat(Field field, XSSFWorkbook workbook, XSSFSheet sheet, int column, int line) {
		Enumeration enumeration = field.getAnnotation(Enumeration.class);
		if (enumeration != null) {
			DataValidationHelper helper = sheet.getDataValidationHelper();
			CellRangeAddressList addressList = new CellRangeAddressList(line + 1, 65535, column, column);

			Enumeration code = field.getAnnotation(Enumeration.class);
			List<SysDictionaryData> datas = dataRepository.findByDtCode(code.value());
			String[] pos = new String[datas.size()];
			for (int i = 0; i < datas.size(); i++) {
				pos[i] = datas.get(i).getName();
			}
			DataValidationConstraint constraint = helper.createExplicitListConstraint(pos);
			DataValidation dataValidation = helper.createValidation(constraint, addressList);
			dataValidation.setSuppressDropDownArrow(true);
			dataValidation.setShowErrorBox(true);
			sheet.addValidationData(dataValidation);
		} else if (field.getType().isAssignableFrom(Date.class)) {
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			XSSFDataFormat format = workbook.createDataFormat();
			cellStyle.setDataFormat(format.getFormat(DATE_FORMAT));
			sheet.setDefaultColumnStyle(column, cellStyle);
		}
	}

	/**
	 * 设置标题
	 * 
	 * @param sheet
	 * @param exportParams
	 * @param workbook
	 */
	void setTitle(XSSFSheet sheet, TemplateExportParams exportParams, XSSFWorkbook workbook, int filedSize) {
		// 合并标题
		CellRangeAddress cra = new CellRangeAddress(exportParams.getTitleLine(), exportParams.getTitleLine(), 0,
				filedSize - 1);
		sheet.addMergedRegion(cra);
		// 标题高度
		XSSFRow titleRow = sheet.createRow(exportParams.getTitleLine());
		titleRow.setHeightInPoints(exportParams.getTitleHeight());
		// 标题
		XSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellValue(exportParams.getTitle());
		// 标题居中
		XSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		titleCell.setCellStyle(titleStyle);
	}

}
