package org.misalen.generator.service;

import java.util.List;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysTableColumn;
import org.misalen.generator.repository.SysTableColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTableColumnService extends CustomService<SysTableColumn, String> {
	@Autowired
	public SysTableColumnRepository sysFormFieldRepository;

	public List<SysTableColumn> findByTableId(String primaryKey) {
		return sysFormFieldRepository.findByTableId(primaryKey);
	}
	
}
