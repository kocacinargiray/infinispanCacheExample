package com.example.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.DepartmentManager;
import com.example.model.DepartmentsCompositeKeys;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class DepartmentManagerRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	@Override
	public @ResponseBody List<DepartmentManager> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<DepartmentManager> criteriaQuery = criteria.createQuery(DepartmentManager.class);
		Root<DepartmentManager> rootEntry = criteriaQuery.from(DepartmentManager.class);
		CriteriaQuery<DepartmentManager> all = criteriaQuery.select(rootEntry);
		TypedQuery<DepartmentManager> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		DepartmentManager departmentManager = new DepartmentManager((DepartmentsCompositeKeys) id);
		departmentManager = emt.find(DepartmentManager.class, (DepartmentsCompositeKeys) id);

		return departmentManager;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		transaction.begin();
		emt.persist((DepartmentManager) entity);
		try {
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		DepartmentManager departmentManager = new DepartmentManager();
		departmentManager = emt.find(DepartmentManager.class, (DepartmentsCompositeKeys) entity);
		if (departmentManager != null) {
			transaction.begin();
			emt.remove(departmentManager);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		DepartmentManager departmentManager = new DepartmentManager();
		departmentManager = (DepartmentManager) entity;
		if (departmentManager != null) {
			transaction.begin();
			emt.merge(departmentManager);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}

	@Override
	public Object findId(Object entity) {
		Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier((DepartmentManager) entity);
		return id;
	}
}
