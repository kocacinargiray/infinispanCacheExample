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

import com.example.model.Employees;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class EmployeesRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	public EmployeesRepository(EntityManager em) {
		this.emt = em;
	}

	public EmployeesRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employees> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<Employees> criteriaQuery = criteria.createQuery(Employees.class);
		Root<Employees> rootEntry = criteriaQuery.from(Employees.class);
		CriteriaQuery<Employees> all = criteriaQuery.select(rootEntry);
		TypedQuery<Employees> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		Employees employee = new Employees();
		employee = emt.find(Employees.class, (int) id);
		return employee;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		transaction.begin();
		emt.persist((Employees) entity);
		try {
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	@Override
	public void delete(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		Employees employee = new Employees();
		employee = emt.find(Employees.class, (int) entity);
		if (employee != null) {
			transaction.begin();
			emt.remove(employee);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}

	@Override
	public void update(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		Employees employee = new Employees();
		employee = (Employees) entity;
		if (employee != null) {
			transaction.begin();
			emt.merge(employee);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}

	}
}
