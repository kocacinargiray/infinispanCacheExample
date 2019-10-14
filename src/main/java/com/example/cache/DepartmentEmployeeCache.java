package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.DepartmentEmployeeCacheListener;
import com.example.model.DepartmentEmployee;
import com.example.model.repository.DepartmentEmployeeRepository;

@Component
public class DepartmentEmployeeCache extends CacheImplementation {

	@Autowired
	DepartmentEmployeeRepository repository;

	Cache<String, DepartmentEmployee> deptEmpCache;

	@Autowired
	public DepartmentEmployeeCache(CacheConfig config, DepartmentEmployeeCacheListener listener) {
		deptEmpCache = config.cacheManager.getCache("deptEmployeeCache");
		deptEmpCache.addListener(listener);
	}

	@Override
	public void addCache(Object entity) {
		DepartmentEmployee entityObject = new DepartmentEmployee();
		entityObject = (DepartmentEmployee) entity;

		if (!deptEmpCache.containsKey(entityObject.cp.toString())) {
			deptEmpCache.put(entityObject.cp.toString(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		DepartmentEmployee entityObject = new DepartmentEmployee();
		entityObject = (DepartmentEmployee) entity;
		deptEmpCache.put(entityObject.cp.toString(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		DepartmentEmployee entityObject = new DepartmentEmployee();
		entityObject = (DepartmentEmployee) entity;
		deptEmpCache.remove(entityObject.cp.toString());

	}

	@Override
	public Cache<String, DepartmentEmployee> getCache() {
		return deptEmpCache;

	}

	@Override
	public DepartmentEmployee findEntity(Object entity) {
		DepartmentEmployee entityObject = (DepartmentEmployee) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<DepartmentEmployee> listEntityObj = repository.findAll();

		for (DepartmentEmployee obj : listEntityObj) {
			addCache(obj);
		}

	}
}
