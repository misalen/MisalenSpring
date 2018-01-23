package org.misalen.generator.controllers;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.misalen.common.advice.structure.RestResult;
import org.misalen.common.annotations.DataAccess;
import org.misalen.common.annotations.ModelComment;
import org.misalen.common.annotations.SerializedField;
import org.misalen.common.annotations.SerializedFields;
import org.misalen.common.utils.PackageUtil;
import org.misalen.common.utils.PageFrom;
import org.misalen.common.utils.TextUtil;
import org.misalen.db.jpa.base.MyImplicitNamingStrategy;
import org.misalen.db.jpa.config.DruidDBConfig;
import org.misalen.generator.domain.SysTableColumn;
import org.misalen.generator.domain.SysTableInfo;
import org.misalen.generator.service.SysTableColumnService;
import org.misalen.generator.service.SysTableInfoService;
import org.misalen.hibernate.DbTableService;
import org.misalen.web.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author DO·VIS
 *
 */
@Controller
@RequestMapping("/sys/table/info")
public class SysTableInfoController extends BaseController {

	@Autowired
	public DruidDBConfig druidDBConfig;
	@Autowired
	public SysTableInfoService sysTableInfoService;
	@Autowired
	public SysTableColumnService sysTableColumnService;

	public DbTableService tableService;

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("sys/table/info/list");
	}

	@PostMapping("/list")
	@SerializedFields({ //
			@SerializedField(resultClass = PageFrom.class), //
			@SerializedField(resultClass = SysTableInfo.class, excludes = { "sysTableColumns" }), //
	})
	public @ResponseBody RestResult<?> list(@RequestBody PageFrom<SysTableInfo> pageFrom)
			throws ClassNotFoundException, SQLException {
		PageFrom<SysTableInfo> from = sysTableInfoService.findPage(pageFrom);
		return renderSuccess(from);
	}

	@GetMapping("/leadin/domain")
	public ModelAndView leadinDomain() throws ClassNotFoundException, SQLException {
		ModelAndView modelAndView = new ModelAndView("sys/table/leadin/domain");
		List<String> infos = PackageUtil.getClassName("org.misalen", Table.class);
		modelAndView.addObject("tableName", infos);
		return modelAndView;
	}

	@GetMapping("/leadin/domain/{tableName}/{cover}")
	public @ResponseBody RestResult<?> leadinDomainForm(@PathVariable String tableName, @PathVariable Boolean cover)
			throws ClassNotFoundException, SQLException {
		Class<?> clazz = Class.forName(tableName);
		ModelComment comment = clazz.getAnnotation(ModelComment.class);
		String name;
		if (comment == null || TextUtil.isNullOrEmpty(comment.value())) {
			name = clazz.getSimpleName();
		} else {
			name = comment.value();
		}
		String ormName = MyImplicitNamingStrategy.addUnderscores(name);
		SysTableInfo formInfo = sysTableInfoService.findByOrmName(ormName);
		if (!cover && formInfo != null) {
			return renderError("是否覆盖生成");
		}
		if (formInfo != null) {
			sysTableInfoService.delete(formInfo);
		}
		formInfo = new SysTableInfo();
		formInfo.setName(name);
		formInfo.setOrmName(ormName);
		formInfo.setOrmType("TABLE");
		sysTableInfoService.save(formInfo);
		for (Field field : clazz.getDeclaredFields()) {
			Transient transient1 = field.getAnnotation(Transient.class);
			if (transient1 != null) {
				continue;
			}
			SysTableColumn column = new SysTableColumn();
			column.setTableId(formInfo.getPrimaryKey());
			column.setName(MyImplicitNamingStrategy.addUnderscores(field.getName()));
			ModelComment comment2 = field.getAnnotation(ModelComment.class);
			if (comment2 != null) {
				column.setRemarks(comment2.value());
			} else {
				column.setRemarks(column.getName());
			}
			Column column2 = field.getAnnotation(Column.class);
			if (column2 != null) {
				column.setLength(column2.length());
				column.setScale(column2.scale());
				column.setNullable(!column2.nullable());
			} else {
				column.setNullable(false);
			}
			DataAccess dataAccess = field.getAnnotation(DataAccess.class);
			if (dataAccess != null) {
				column.setAccessAdd(dataAccess.add());
				column.setAccessUpdate(dataAccess.update());
				column.setAccessList(dataAccess.list());
				column.setAccessSearch(dataAccess.search());
			}
			column.setType(field.getType().getName());
			sysTableColumnService.save(column);
		}
		return renderSuccess();
	}

	@GetMapping("/leadin/database")
	public ModelAndView add() throws ClassNotFoundException, SQLException {
		ModelAndView modelAndView = new ModelAndView("sys/table/leadin/database");
		List<SysTableInfo> infos = tableService.getTables(null);
		for (SysTableInfo sysTableInfo : infos) {
			if (TextUtil.isNullOrEmpty(sysTableInfo.getName())) {
				sysTableInfo.setName(sysTableInfo.getOrmName());
			}
		}
		modelAndView.addObject("tableName", infos);
		return modelAndView;
	}

	@GetMapping("/leadin/database/{tableName}/{cover}")
	public @ResponseBody RestResult<?> form(@PathVariable String tableName, @PathVariable Boolean cover)
			throws ClassNotFoundException, SQLException {
		SysTableInfo formInfo = sysTableInfoService.findByOrmName(tableName);
		if (!cover && formInfo != null) {
			return renderError("是否覆盖生成");
		}
		if (formInfo != null) {
			sysTableInfoService.delete(formInfo);
		}
		formInfo = tableService.getTables(tableName).get(0);
		if (TextUtil.isNullOrEmpty(formInfo.getName())) {
			formInfo.setName(formInfo.getOrmName());
		}
		sysTableInfoService.save(formInfo);
		List<SysTableColumn> fields = tableService.getColumns(tableName, null);
		for (SysTableColumn sysFormField : fields) {
			sysFormField.setTableId(formInfo.getPrimaryKey());
			sysTableColumnService.save(sysFormField);
		}
		return renderSuccess();
	}

	@GetMapping("/del/{primaryKey}")
	public @ResponseBody RestResult<?> del(@PathVariable String primaryKey) {
		sysTableInfoService.delete(primaryKey);
		return renderSuccess();
	}

	@ModelAttribute
	public void init() {
		if (tableService == null) {
			tableService = new DbTableService(druidDBConfig.getDbUrl(), druidDBConfig.getUsername(),
					druidDBConfig.getPassword(), druidDBConfig.getDriverClassName());
		}
	}
}
