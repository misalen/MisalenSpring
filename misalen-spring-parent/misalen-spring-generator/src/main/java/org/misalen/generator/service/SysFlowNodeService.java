package org.misalen.generator.service;

import java.util.List;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.generator.domain.SysFlowNode;
import org.misalen.generator.repository.SysFlowNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author DO·VIS
 *
 */
@Service
public class SysFlowNodeService extends CustomService<SysFlowNode, String> {
	@Autowired
	public SysFlowNodeRepository sysFlowNodeRepository;

	public List<SysFlowNode> findByFlowId(String primaryKey) {
		return sysFlowNodeRepository.findByFlowId(primaryKey);
	}

	public boolean allowAdd(String nodeType,String flowId) {
		if (nodeType.equals("start") || nodeType.equals("stop")) {
			return sysFlowNodeRepository.countByNodeTypeAndFlowId(nodeType,flowId) == 0;
		}
		return true;
	}

}
