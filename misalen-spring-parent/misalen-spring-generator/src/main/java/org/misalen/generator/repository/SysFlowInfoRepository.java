package org.misalen.generator.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysFlowInfo;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author DO·VIS
 *
 */
@Repository
public interface SysFlowInfoRepository extends BaseRepository<SysFlowInfo, String> {
	
	
}