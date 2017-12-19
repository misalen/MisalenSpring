package org.misalen.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.misalen.common.utils.TextUtil;
import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.system.domain.SysAdminRole;
import org.misalen.system.repository.SysAdminRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysAdminRoleService extends CustomService<SysAdminRole, String> {
	@Autowired
	public SysAdminRoleRepository sysAdminRoleRepository;

	@Transactional
	public void deleteByAdminId(String primaryKey) {
		sysAdminRoleRepository.deleteByAdminId(primaryKey);
	}

	public void addAdminRole(String primaryKey, String[] roleIds) {
		List<SysAdminRole> entities = new ArrayList<SysAdminRole>();
		if(roleIds.length>0&&!roleIds[0].equals("undefined")&&!TextUtil.isNullOrEmpty(roleIds[0])){
			for (String roleId : roleIds) {
				SysAdminRole adminRole = new SysAdminRole();
				adminRole.setAddTime(new Date());
				adminRole.setAdminId(primaryKey);
				adminRole.setRoleId(roleId);
				entities.add(adminRole);
			}
		}
		sysAdminRoleRepository.saveAll(entities);
	}
}
