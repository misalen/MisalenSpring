package org.misalen.web.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.web.domain.SysDictionary;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictionaryRepository extends BaseRepository<SysDictionary, String> {
	
	public SysDictionary getByCode(String code);
	
}