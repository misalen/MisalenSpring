package org.misalen.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.misalen.ServerApplication;
import org.misalen.poi.excle.SysAdmin;
import org.misalen.poi.excle.export.TemplateExport;
import org.misalen.poi.excle.export.TemplateExportParams;
import org.misalen.poi.excle.importe.TemplateImport;
import org.misalen.poi.excle.importe.TemplateImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExcelPoiTest {
	static String filepath = "C:/Users/zhaoguochao/Desktop/myExcel.xlsx";
	@Autowired
	TemplateImport templateImport;
	@Autowired
	TemplateExport templateExport;

	@Test
	public void main() {
		try {
			//exportExcel();
			 importExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void importExcel() throws Exception {
		InputStream is = new FileInputStream(filepath);
		TemplateImportParams importParams = new TemplateImportParams();
		importParams.setTitleHidden(true);
		List<SysAdmin> admins = templateImport.parsing(importParams, is, SysAdmin.class);
		for (SysAdmin sysAdmin : admins) {
			System.err.println(sysAdmin.toString());
		}
	}

	private void exportExcel() throws IOException {
		TemplateExportParams exportParams = new TemplateExportParams();
		exportParams.setTitleHidden(true);
		XSSFWorkbook workbook = templateExport.export(exportParams, SysAdmin.class); // 创建一个excel
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