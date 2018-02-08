package org.misalen.workflow.activiti.user;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;

public class MyCompanyGroupManager extends GroupEntityManager {

	@Override
	public List<Group> findGroupsByUser(String userId) {
		return super.findGroupsByUser(userId);
	}

	@Override
	public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
		return super.findGroupByQueryCriteria(query, page);
	}

	@Override
	public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
		return super.findGroupCountByQueryCriteria(query);
	}

	@Override
	public Group createNewGroup(String groupId) {
		return super.createNewGroup(groupId);
		// throw new UnsupportedOperationException();
	}

	@Override
	public void deleteGroup(String groupId) {
		super.deleteGroup(groupId);
		// throw new UnsupportedOperationException();
	}
}