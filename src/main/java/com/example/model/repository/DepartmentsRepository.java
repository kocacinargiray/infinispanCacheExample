package com.example.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Departments;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class DepartmentsRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	@Override
	public @ResponseBody List<Departments> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<Departments> criteriaQuery = criteria.createQuery(Departments.class);
		Root<Departments> rootEntry = criteriaQuery.from(Departments.class);
		CriteriaQuery<Departments> all = criteriaQuery.select(rootEntry);
		TypedQuery<Departments> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		Departments department = new Departments();
		department = emt.find(Departments.class, String.valueOf(id));
		return department;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void save(Object entity) {
		emt.persist((Departments) entity);

	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Object entity) {
		Departments department = new Departments();
		department = emt.find(Departments.class, String.valueOf(entity));
		if (department != null) {
			emt.remove(department);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Object entity) {
		Departments department = new Departments();
		department = (Departments) entity;
		if (department != null) {
			emt.merge(department);
		}
	}

	@Override
	public Object findId(Object entity) {
		Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier((Departments) entity);
		return id;
	}
}
