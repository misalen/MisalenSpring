package org.misalen.db.jpa.base.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 获取entityManager
	 * 
	 * @return entityManager entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 设置entityManager
	 * 
	 * @param entityManager
	 *            entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}