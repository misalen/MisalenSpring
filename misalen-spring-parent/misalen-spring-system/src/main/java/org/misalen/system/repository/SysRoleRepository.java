package org.misalen.system.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.system.domain.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends BaseRepository<SysRole, String> {

	@Query("select r from SysRole r left join r.sysAdminRoles a where a.adminId=?1")
	public List<SysRole> findByAdminId(String adminId);

	@Query("from SysRole order by addTime")
	public List<SysRole> findOrderByAddTime();

}