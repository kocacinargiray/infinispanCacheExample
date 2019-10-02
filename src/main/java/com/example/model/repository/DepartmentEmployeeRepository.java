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

import com.example.model.DepartmentEmployee;
import com.example.model.DepartmentsCompositeKeys;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class DepartmentEmployeeRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	public DepartmentEmployeeRepository(EntityManager em) {
		this.emt = em;
	}

	public DepartmentEmployeeRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public @ResponseBody List<DepartmentEmployee> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<DepartmentEmployee> criteriaQuery = criteria.createQuery(DepartmentEmployee.class);
		Root<DepartmentEmployee> rootEntry = criteriaQuery.from(DepartmentEmployee.class);
		CriteriaQuery<DepartmentEmployee> all = criteriaQuery.select(rootEntry);
		TypedQuery<DepartmentEmployee> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		/*
		 * DepartmentEmployee departmentEmployee = new
		 * DepartmentEmployee((DepartmentsCompositeKeys) id); CriteriaBuilder criteria =
		 * emt.getCriteriaBuilder(); CriteriaQuery<DepartmentEmployee> criteriaQuery =
		 * criteria.createQuery(DepartmentEmployee.class); Root<DepartmentEmployee>
		 * rootEntry = criteriaQuery.from(DepartmentEmployee.class); Predicate
		 * predicateEmpNo = criteria.equal(rootEntry.get("emp_np"),
		 * departmentEmployee.getEmpNo()); Predicate predicateDeptNo =
		 * criteria.equal(rootEntry.get("dept_no"), departmentEmployee.getDeptNo());
		 * Predicate lastPredicate = criteria.and(predicateEmpNo, predicateDeptNo);
		 * CriteriaQuery<DepartmentEmployee> all =
		 * criteriaQuery.select(rootEntry).where(lastPredicate);
		 * TypedQuery<DepartmentEmployee> tQuery = emt.createQuery(all);
		 */
		DepartmentEmployee departmentEmployee = new DepartmentEmployee();
		departmentEmployee = emt.find(DepartmentEmployee.class, (DepartmentsCompositeKeys) id);

		return departmentEmployee;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Object entity) {
		EntityTransaction transaction = emt.getTransaction();
		transaction.begin();
		emt.persist((DepartmentEmployee) entity);
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
		DepartmentEmployee departmentEmployee = new DepartmentEmployee();
		departmentEmployee = emt.find(DepartmentEmployee.class, (DepartmentsCompositeKeys) entity);
		if (departmentEmployee != null) {
			transaction.begin();
			emt.remove(departmentEmployee);
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
		DepartmentEmployee departmentEmployee = new DepartmentEmployee();
		departmentEmployee = (DepartmentEmployee) entity;
		if (departmentEmployee != null) {
			transaction.begin();
			emt.merge(departmentEmployee);
			try {
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
			}
		}
	}
}
