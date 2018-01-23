package org.misalen.permission.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.permission.domain.SysRole;
import org.misalen.permission.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService extends CustomService<SysRole, String> {
	@Autowired
	public SysRoleRepository sysRoleRepository;

	public Set<String> findByAdminId(String adminId) {
		List<SysRole> sysRoles = sysRoleRepository.findByAdminId(adminId);
		Set<String> roleSet = new HashSet<String>();
		for (SysRole sysRole : sysRoles) {
			roleSet.add(sysRole.getPrimaryKey());
		}
		return roleSet;
	}

	public List<SysRole> findOrderByAddTime() {
		return sysRoleRepository.findOrderByAddTime();
	}
}
