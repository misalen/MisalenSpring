package org.misalen.generator.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysFlowNodeAtta;
import org.misalen.generator.repository.SysFlowNodeAttaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author DO·VIS
 *
 */
@Service
public class SysFlowNodeAttaService extends CustomService<SysFlowNodeAtta, String> {
	@Autowired
	public SysFlowNodeAttaRepository sysFlowNodeAttaRepository;


}
