package org.misalen.generator.service;

import java.util.List;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysFormField;
import org.misalen.generator.repository.SysFormFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysFormFieldService extends CustomService<SysFormField, String> {

	@Autowired
	public SysFormFieldRepository sysFormFieldRepository;

	public void deleteByFormId(String primaryKey) {
		sysFormFieldRepository.deleteByFormId(primaryKey);
	}

	public List<SysFormField> findByFormId(String primaryKey) {
		return sysFormFieldRepository.findByFormId(primaryKey);
	}

}
