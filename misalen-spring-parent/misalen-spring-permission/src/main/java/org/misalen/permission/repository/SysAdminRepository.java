package org.misalen.permission.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.permission.domain.SysAdmin;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRepository extends BaseRepository<SysAdmin, String> {
	
	public SysAdmin getByUsername(String username);
	
}