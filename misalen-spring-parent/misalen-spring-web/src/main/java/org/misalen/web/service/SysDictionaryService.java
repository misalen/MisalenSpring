package org.misalen.web.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.web.domain.SysDictionary;
import org.misalen.web.repository.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "dictionary")
public class SysDictionaryService extends CustomService<SysDictionary, String> {
	@Autowired
	public SysDictionaryRepository sysDictionaryRepository;

	@Cacheable
	public SysDictionary getByCode(String code) {
		return sysDictionaryRepository.getByCode(code);
	}
}
