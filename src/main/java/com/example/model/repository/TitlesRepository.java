package com.example.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.TitleCompositeKey;
import com.example.model.Titles;
import com.example.model.repository.repositoryinterface.RepositoryInterface;

@Transactional(readOnly = true)
@Repository
public class TitlesRepository implements RepositoryInterface {

	@PersistenceContext
	private EntityManager emt;

	@Override
	public @ResponseBody List<Titles> findAll() {
		CriteriaBuilder criteria = emt.getCriteriaBuilder();
		CriteriaQuery<Titles> criteriaQuery = criteria.createQuery(Titles.class);
		Root<Titles> rootEntry = criteriaQuery.from(Titles.class);
		CriteriaQuery<Titles> all = criteriaQuery.select(rootEntry);
		TypedQuery<Titles> tQuery = emt.createQuery(all);
		return tQuery.getResultList();
	}

	@Override
	public Object findById(Object id) {
		Titles titles = new Titles();
		titles = emt.find(Titles.class, (TitleCompositeKey) id);
		return titles;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Object entity) {
		emt.persist((Titles) entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Object entity) {
		Titles title = new Titles();
		title = emt.find(Titles.class, (TitleCompositeKey) entity);
		if (title != null) {
			emt.remove(title);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Object entity) {
		Titles title = new Titles();
		title = (Titles) entity;
		if (title != null) {
			emt.merge(title);
		}
	}

	@Override
	public Object findId(Object entity) {
		Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier((Titles) entity);
		return id;
	}
}
