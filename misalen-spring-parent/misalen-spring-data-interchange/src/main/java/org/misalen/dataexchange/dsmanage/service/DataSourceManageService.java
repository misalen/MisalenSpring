package org.misalen.dataexchange.dsmanage.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.dataexchange.dsmanage.domain.DataSourceManage;
import org.misalen.dataexchange.dsmanage.repository.DataSourceManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author DO.VIS
 *
 */
@Service
public class DataSourceManageService extends CustomService<DataSourceManage, String> {
	@Autowired
	public DataSourceManageRepository dataSourceManageRepository;
	
}
