package org.misalen.permission.repository;

import java.util.List;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.permission.domain.SysRoleResources;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleResourcesRepository extends BaseRepository<SysRoleResources, String> {

	public void deleteByRoleId(String roleId);

	@Query(nativeQuery = true, value = "SELECT RE.RESOURCE_URL,RO.PRIMARY_KEY FROM SYS_ROLE RO LEFT JOIN SYS_ROLE_RESOURCES M ON RO.PRIMARY_KEY=M.ROLE_ID LEFT JOIN SYS_RESOURCES RE ON M.RESOURCES_ID=RE.PRIMARY_KEY WHERE RE.RESOURCE_URL IS NOT NULL AND RE.RESOURCE_URL !=''")
	public List<Object[]> findSystemPerm();
}