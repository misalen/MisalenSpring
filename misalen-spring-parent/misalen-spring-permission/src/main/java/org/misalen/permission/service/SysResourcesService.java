package org.misalen.permission.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.misalen.common.utils.ListUtil;
import org.misalen.db.jpa.base.service.CustomService;
import org.misalen.permission.domain.SysResources;
import org.misalen.permission.domain.SysResources.SaveType;
import org.misalen.permission.repository.SysResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @CacheConfig(cacheNames = "users")
public class SysResourcesService extends CustomService<SysResources, String> {
	@Autowired
	public SysResourcesRepository sysResourcesRepository;

	// @Cacheable
	public List<SysResources> getAllTree() {
		return sysResourcesRepository.findBySaveTypeOrderBySeq(SaveType.menu1.name());
	}

	public SysResources findByTextAndParentId(String text, String parentId) {
		return sysResourcesRepository.findByTextAndParentId(text, parentId);
	}

	public SysResources findByText(String text) {
		return sysResourcesRepository.findByTextAndSaveType(text, SaveType.menu1.name());
	}

	public Set<String> findByRoleId(String roleId) {
		List<SysResources> sysRoles = sysResourcesRepository.findByRoleId(roleId);
		Set<String> resourcesSet = new HashSet<String>();
		for (SysResources sysResources : sysRoles) {
			resourcesSet.add(sysResources.getPrimaryKey());
		}
		return resourcesSet;
	}

	// @Cacheable
	public List<SysResources> getUserMenu(Set<String> role) {
		Set<String> saveType = new HashSet<String>();
		saveType.add(SaveType.menu1.name());
		saveType.add(SaveType.menu2.name());
		List<SysResources> resources = sysResourcesRepository.findInRole(role, saveType);
		Map<String, SysResources> map = new TreeMap<String, SysResources>();
		for (SysResources sysResources : resources) {
			map.put(sysResources.getPrimaryKey(), sysResources);
		}
		Iterator<Map.Entry<String, SysResources>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, SysResources> entry = it.next();
			SysResources sysResource = entry.getValue();
			if (sysResource.getSaveType().equals(SaveType.menu2.name())) {
				if (map.containsKey(sysResource.getParentId())) {
					SysResources parent = map.get(sysResource.getParentId());
					if (parent.getChildren() == null) {
						parent.setChildren(new LinkedList<SysResources>());
					}
					parent.getChildren().add(sysResource);
				} else {
					SysResources parent = sysResourcesRepository.findOne(sysResource.getParentId());
					if (parent != null) {
						parent.setChildren(new LinkedList<SysResources>());
						parent.getChildren().add(sysResource);
						map.put(parent.getPrimaryKey(), parent);
					}
				}
				it.remove();
			} else if (sysResource.getSaveType().equals(SaveType.menu1.name())) {

			}
		}
		List<SysResources> returnResources = new ArrayList<SysResources>();
		for (SysResources sysResources : map.values()) {
			if (!ListUtil.isNullOrEmpty(sysResources.getChildren())) {
				sysResources.getChildren().sort((h1, h2) -> h1.getSeq().compareTo(h2.getSeq()));
			}
			returnResources.add(sysResources);
		}
		returnResources.sort((h1, h2) -> h1.getSeq().compareTo(h2.getSeq()));
		return returnResources;
	}

}
