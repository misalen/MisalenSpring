package org.misalen.generator.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysTableInfo;
import org.misalen.generator.repository.SysTableInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysTableInfoService extends CustomService<SysTableInfo, String> {
	@Autowired
	public SysTableInfoRepository sysFormInfoRepository;

	public SysTableInfo findByOrmName(String ormName) {
		return sysFormInfoRepository.findByOrmName(ormName);
	}
	

}
