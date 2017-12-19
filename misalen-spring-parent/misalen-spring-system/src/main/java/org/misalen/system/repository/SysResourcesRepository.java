package org.misalen.system.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.misalen.db.jpa.base.repository.BaseRepository;
import org.misalen.system.domain.SysResources;
import org.misalen.system.repository.SysResourcesRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourcesRepository extends SysResourcesRepositoryCustom, BaseRepository<SysResources, String> {

	@Query("select r from SysResources r left join r.sysRoleResources rr where r.usingState='available' and rr.roleId in(?1) and r.saveType in(?2) order by seq asc")
	public LinkedList<SysResources> findInRole(Set<String> role, Set<String> saveType);

	public LinkedList<SysResources> findBySaveTypeOrderBySeq(String name);

	@Query("select r from SysResources r left join r.sysRoleResources a where a.roleId=?1")
	public List<SysResources> findByRoleId(String roleId);

	public SysResources findByText(String text);

	public SysResources findByTextAndSaveType(String text, String name);

	public SysResources findByTextAndParentId(String text, String parentId);
}