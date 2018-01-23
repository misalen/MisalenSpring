package org.misalen.permission.service;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.permission.domain.SysAdmin;
import org.misalen.permission.repository.SysAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SysAdminService extends CustomService<SysAdmin, String> {
	@Autowired
	public SysAdminRepository sysAdminRepository;
	
	public SysAdmin getByUsername(String username) {
		return sysAdminRepository.getByUsername(username);
	}

}
