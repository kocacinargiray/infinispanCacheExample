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

import com.example.model.Salaries;
import com.example.model.SalariesCompositeKeys;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class SalariesRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	public SalariesRepository(EntityManager em) {
		this.emt = em;
	}

	@Override
	public @ResponseBody List<Salaries> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<Salaries> criteriaQuery = criteria.createQuery(Salaries.class);
		Root<Salaries> rootEntry = criteriaQuery.from(Salaries.class);
		CriteriaQuery<Salaries> all = criteriaQuery.select(rootEntry);
		TypedQuery<Salaries> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		Salaries salary = new Salaries();
		salary = emt.find(Salaries.class, (SalariesCompositeKeys) id);
		return salary;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		transaction.begin();
		emt.persist((Salaries) entity);
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
		Salaries salary = new Salaries();
		salary = emt.find(Salaries.class, (SalariesCompositeKeys) entity);
		if (salary != null) {
			transaction.begin();
			emt.remove(salary);
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
		Salaries salary = new Salaries();
		salary = (Salaries) entity;
		if (salary != null) {
			transaction.begin();
			emt.merge(salary);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}
}
