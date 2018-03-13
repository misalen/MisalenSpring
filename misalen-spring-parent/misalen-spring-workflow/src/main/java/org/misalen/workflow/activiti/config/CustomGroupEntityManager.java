package org.misalen.workflow.activiti.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.misalen.permission.domain.SysRole;
import org.misalen.permission.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomGroupEntityManager extends GroupEntityManager {
	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Override
	public List<Group> findGroupsByUser(String userId) {
		if (userId == null)
			return null;
		List<Group> groupEntities = new ArrayList<Group>();
		List<SysRole> list = sysRoleRepository.findByAdminId(userId);
		for (SysRole sysRole : list) {
			GroupEntity groupEntity = new GroupEntity();
			groupEntity.setRevision(1);
			groupEntity.setType("assignment");
			groupEntity.setId(sysRole.getCode());
			groupEntity.setName(sysRole.getText());
			groupEntities.add(groupEntity);
		}
		return groupEntities;
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public Group createNewGroup(String groupId) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public void deleteGroup(String groupId) {
		throw new RuntimeException("not implement method.");
	}
}