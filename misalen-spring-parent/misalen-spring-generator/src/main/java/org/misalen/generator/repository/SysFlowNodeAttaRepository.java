package org.misalen.generator.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysFlowNodeAtta;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author DO·VIS
 *
 */
@Repository
public interface SysFlowNodeAttaRepository extends BaseRepository<SysFlowNodeAtta, String> {

	void deleteByNodeIdIn(List<String> nodeIds);

}