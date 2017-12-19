package org.misalen.system.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.system.domain.SysAdminRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRoleRepository extends BaseRepository<SysAdminRole, String> {

	public void deleteByAdminId(String adminId);
}