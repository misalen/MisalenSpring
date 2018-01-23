package org.misalen.web.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.web.domain.SysDictionaryData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictionaryDataRepository extends BaseRepository<SysDictionaryData, String> {

	public List<SysDictionaryData> findByDtId(String dtId);
	
	@Query("select r from SysDictionary a left join a.sysDictionaryDatas r where a.code=?1")
	public List<SysDictionaryData> findByDtCode(String code);

}