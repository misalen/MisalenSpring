package org.misalen.permission.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.misalen.common.utils.TextUtil;
import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.permission.domain.SysRoleResources;
import org.misalen.permission.repository.SysRoleResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRoleResourcesService extends CustomService<SysRoleResources, String> {
	@Autowired
	public SysRoleResourcesRepository sysRoleResourcesRepository;

	@Transactional
	public void deleteByRoleId(String primaryKey) {
		sysRoleResourcesRepository.deleteByRoleId(primaryKey);
	}

	public void addRoleResources(String primaryKey, String[] resourcesIds) {
		List<SysRoleResources> entities = new ArrayList<SysRoleResources>();
		if (resourcesIds.length > 0 && !resourcesIds[0].equals("undefined")
				&& !TextUtil.isNullOrEmpty(resourcesIds[0])) {
			for (String resourcesId : resourcesIds) {
				SysRoleResources sysRoleResources = new SysRoleResources();
				sysRoleResources.setAddTime(new Date());
				sysRoleResources.setResourcesId(resourcesId);
				sysRoleResources.setRoleId(primaryKey);
				entities.add(sysRoleResources);
			}
		}
		sysRoleResourcesRepository.save(entities);
	}

	public List<Object[]> getSystemPerm() {
		List<Object[]> list = sysRoleResourcesRepository.findSystemPerm();
		return list;
	}
}
