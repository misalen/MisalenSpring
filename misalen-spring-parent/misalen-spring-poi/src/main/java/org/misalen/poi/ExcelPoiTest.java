package org.misalen.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.misalen.poi.export.TemplateExportParams;
import org.misalen.poi.export.TemplateExportUtil;
import org.misalen.poi.importe.TemplateImportParams;
import org.misalen.poi.importe.TemplateImportUtil;

public class ExcelPoiTest {
	static String filepath = "C:/Users/zhaoguochao/Desktop/myExcel.xlsx";

	public static void main(String[] args) throws Exception {
		// exportExcel();
		importExcel();
	}

	private static void importExcel() throws Exception {
		InputStream is = new FileInputStream(filepath);
		TemplateImportParams importParams = new TemplateImportParams();
		importParams.setTitleHidden(true);
		List<SysAdmin> admins = new TemplateImportUtil().parsing(importParams, is, SysAdmin.class);

	}

	private static void exportExcel() throws IOException {
		TemplateExportParams exportParams = new TemplateExportParams();
		exportParams.setTitleHidden(true);
		// exportParams.setSheetName("ceshi");
		// exportParams.setTitle("员工名单收集");
		XSSFWorkbook workbook = new TemplateExportUtil().export(exportParams, SysAdmin.class); // 创建一个excel
		// 输出到本地
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filepath);
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