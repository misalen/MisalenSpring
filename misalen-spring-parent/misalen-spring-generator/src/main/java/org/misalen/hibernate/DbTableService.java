package org.misalen.hibernate;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.misalen.common.utils.TextUtil;
import org.misalen.generator.domain.SysTableColumn;
import org.misalen.generator.domain.SysTableInfo;
import org.misalen.hibernate.cfg.reveng.dialect.MetaDataDialect;
import org.misalen.hibernate.cfg.reveng.dialect.MySQLMetaDataDialect;
import org.misalen.hibernate.tool.util.JDBCToJavaTypeHelper;

public class DbTableService {
	String dbUrl;
	String username;
	String pwd;
	String driverClassName;

	public DbTableService(String dbUrl, String username, String pwd, String driverClassName) {
		this.dbUrl = dbUrl;
		this.username = username;
		this.pwd = pwd;
		this.driverClassName = driverClassName;
	}

	public List<SysTableInfo> getTables(String table) throws ClassNotFoundException, SQLException {
		MetaDataDialect dataDialect = getDialect();
		Iterator<Map<String, Object>> it = dataDialect.getTables(null, null, table);
		List<SysTableInfo> tables = new LinkedList<SysTableInfo>();
		while (it.hasNext()) {
			Map<String, Object> entry = it.next();
			SysTableInfo sysFormInfo = new SysTableInfo();
			sysFormInfo.setName(String.valueOf(entry.get("REMARKS")));
			sysFormInfo.setOrmName(String.valueOf(entry.get("TABLE_NAME")));
			sysFormInfo.setOrmType(String.valueOf(entry.get("TABLE_TYPE")));
			tables.add(sysFormInfo);
		}
		dataDialect.close();
		return tables;
	}

	public List<SysTableColumn> getColumns(String tableName, String column)
			throws ClassNotFoundException, SQLException {
		MetaDataDialect dataDialect = getDialect();
		Iterator<Map<String, Object>> it = dataDialect.getColumns(null, null, tableName, column);
		List<SysTableColumn> columns = new LinkedList<SysTableColumn>();
		while (it.hasNext()) {
			Map<String, Object> entry = it.next();
			SysTableColumn dbColumn = new SysTableColumn();
			String remarks=String.valueOf(entry.get("REMARKS"));
			if(TextUtil.isNullOrEmpty(remarks)){
				remarks=String.valueOf(entry.get("COLUMN_NAME"));
			}
			dbColumn.setRemarks(remarks);
			dbColumn.setName(String.valueOf(entry.get("COLUMN_NAME")));
			dbColumn.setNullable(String.valueOf(entry.get("NULLABLE")).equals("0"));
			dbColumn.setLength(Integer.valueOf(String.valueOf(entry.get("COLUMN_SIZE"))));
			dbColumn.setScale(Integer.valueOf(String.valueOf(entry.get("DECIMAL_DIGITS"))));
			dbColumn.setType(JDBCToJavaTypeHelper.getPreferredHibernateType(
					Integer.valueOf(String.valueOf(entry.get("DATA_TYPE"))), dbColumn.getLength(), dbColumn.getLength(),
					Integer.valueOf(String.valueOf(entry.get("DECIMAL_DIGITS"))), dbColumn.getNullable(), false));
			if (!dbColumn.getName().equals("PRIMARY_KEY") && !dbColumn.getName().equals("ADD_TIME")) {
				columns.add(dbColumn);
			}
		}
		dataDialect.close();
		return columns;
	}

	private MetaDataDialect getDialect() throws ClassNotFoundException, SQLException {
		MetaDataDialect dataDialect = new MySQLMetaDataDialect();
		dataDialect.configure(username, pwd, dbUrl, driverClassName);
		return dataDialect;
	}
}
