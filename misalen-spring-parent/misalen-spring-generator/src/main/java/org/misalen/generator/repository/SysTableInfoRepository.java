package org.misalen.generator.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysTableInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SysTableInfoRepository extends BaseRepository<SysTableInfo, String> {

	SysTableInfo findByOrmName(String ormName);
	
}