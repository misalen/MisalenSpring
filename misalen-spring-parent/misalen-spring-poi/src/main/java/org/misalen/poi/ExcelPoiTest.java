package org.misalen.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPoiTest {

	public static void main(String[] args) throws IOException {
		TemplateExportParams exportParams = new TemplateExportParams();
		exportParams.setSheetName("ceshi");
		exportParams.setTitle("员工名单收集");
		XSSFWorkbook workbook = TemplateExportUtil.export(exportParams, SysAdmin.class); // 创建一个excel
		// 输出到本地
		String excelName = "C:/Users/zhaoguochao/Desktop/myExcel.xlsx";
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(excelName);
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			out = null;
		}
		workbook.close();
	}

}