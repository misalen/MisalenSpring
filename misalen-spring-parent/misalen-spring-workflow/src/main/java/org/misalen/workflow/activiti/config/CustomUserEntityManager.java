package org.misalen.workflow.activiti.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.misalen.permission.domain.SysAdmin;
import org.misalen.permission.domain.SysRole;
import org.misalen.permission.repository.SysAdminRepository;
import org.misalen.permission.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomUserEntityManager extends UserEntityManager {
	@Autowired
	private SysAdminRepository sysAdminRepository;

	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Override
	public User findUserById(final String userId) {
		if (userId == null) {
			return null;
		}
		SysAdmin sysAdmin = sysAdminRepository.getOne(userId);
		UserEntity entity = new UserEntity();
		entity.setId(sysAdmin.getUsername());
		entity.setFirstName(sysAdmin.getNickname());
		return entity;

	}

	@Override
	public List<Group> findGroupsByUser(final String userId) {
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
	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public long findUserCountByQueryCriteria(UserQueryImpl query) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public User createNewUser(String userId) {
		throw new RuntimeException("not implement method.");
	}

	@Override
	public void deleteUser(String userId) {
		throw new RuntimeException("not implement method.");
	}
}
