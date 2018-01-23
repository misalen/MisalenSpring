package org.misalen.web.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.web.domain.SysConversion;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author DO·VIS
 *
 */
@Repository
public interface SysConversionRepository extends BaseRepository<SysConversion, String> {

	SysConversion findByOriginal(String original);
	
	Long countByEscapeLike(String escape);
}