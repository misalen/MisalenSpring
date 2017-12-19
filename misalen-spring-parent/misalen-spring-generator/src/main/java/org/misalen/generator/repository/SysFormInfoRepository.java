package org.misalen.generator.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.generator.domain.SysFormInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface SysFormInfoRepository extends BaseRepository<SysFormInfo, String> {

}