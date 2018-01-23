package org.misalen.permission.repository;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.permission.domain.SysAdminRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminRoleRepository extends BaseRepository<SysAdminRole, String> {

	public void deleteByAdminId(String adminId);
}