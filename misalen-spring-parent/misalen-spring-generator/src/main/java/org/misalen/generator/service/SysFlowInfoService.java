package org.misalen.generator.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysFlowInfo;
import org.misalen.generator.repository.SysFlowInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author DO·VIS
 *
 */
@Service
public class SysFlowInfoService extends CustomService<SysFlowInfo, String> {
	@Autowired
	public SysFlowInfoRepository sysFlowInfoRepository;
	
}
