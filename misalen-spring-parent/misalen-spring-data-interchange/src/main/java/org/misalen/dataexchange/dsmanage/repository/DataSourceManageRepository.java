package org.misalen.dataexchange.dsmanage.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.dataexchange.dsmanage.domain.DataSourceManage;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author DO.VIS
 *
 */
@Repository
public interface DataSourceManageRepository extends BaseRepository<DataSourceManage, String> {
	
	
}