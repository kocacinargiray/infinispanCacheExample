package com.example.model.repository.repositoryinterface;

import java.util.List;

public interface RepositoryInterface {

	public List<?> findAll();

	public Object findById(Object id);

	public void save(Object entity);

	public void delete(Object entity);

	public void update(Object entity);

}
