package org.misalen.system.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.system.domain.SysDictionary;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictionaryRepository extends BaseRepository<SysDictionary, String> {
	
	public SysDictionary getByCode(String code);
	
}