package org.misalen.generator.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysFlowNode;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author DO·VIS
 *
 */
@Repository
public interface SysFlowNodeRepository extends BaseRepository<SysFlowNode, String> {

	List<SysFlowNode> findByFlowId(String primaryKey);

	void deleteByFlowId(String flowId);

	Long countByNodeTypeAndFlowId(String nodeType, String flowId);

}