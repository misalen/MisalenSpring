package org.misalen.web.service;

import java.util.List;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.web.domain.SysDictionaryData;
import org.misalen.web.repository.SysDictionaryDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "dictionaryData")
public class SysDictionaryDataService extends CustomService<SysDictionaryData, String> {
	@Autowired
	public SysDictionaryDataRepository sysDictionaryDataRepository;

	public List<SysDictionaryData> findByDtId(String dtId) {
		return sysDictionaryDataRepository.findByDtId(dtId);
	}
	public List<SysDictionaryData> findByDtCode(String code) {
		return sysDictionaryDataRepository.findByDtCode(code);
	}
}
