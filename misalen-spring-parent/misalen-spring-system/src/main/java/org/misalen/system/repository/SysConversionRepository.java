package org.misalen.system.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.system.domain.SysConversion;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author DO·VIS
 *
 */
@Repository
public interface SysConversionRepository extends BaseRepository<SysConversion, String> {

	SysConversion findByChinese(String chinese);
	
	Long countByPinyinLike(String pinyin);
}