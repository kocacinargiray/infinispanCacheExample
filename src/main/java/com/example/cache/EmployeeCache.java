package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.EmployeeCacheListener;
import com.example.model.Employees;
import com.example.model.repository.EmployeesRepository;

@Component
public class EmployeeCache extends CacheImplementation {

	@Autowired
	EmployeesRepository repository;

	Cache<Integer, Employees> deptCache;

	@Autowired
	public EmployeeCache(CacheConfig config, EmployeeCacheListener listener) {
		deptCache = config.cacheManager.getCache("empCache");
		deptCache.addListener(listener);
	}

	@Override
	public void addCache(Object entity) {
		Employees entityObject = new Employees();
		entityObject = (Employees) entity;

		if (!deptCache.containsKey(entityObject.getEmpNo())) {
			deptCache.put(entityObject.getEmpNo(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		Employees entityObject = new Employees();
		entityObject = (Employees) entity;
		deptCache.put(entityObject.getEmpNo(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		Employees entityObject = new Employees();
		entityObject = (Employees) entity;
		deptCache.remove(entityObject.getEmpNo());

	}

	@Override
	public Cache<Integer, Employees> getCache() {
		return deptCache;

	}

	@Override
	public Employees findEntity(Object entity) {
		Employees entityObject = (Employees) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<Employees> listEntityObj = repository.findAll();

		for (Employees obj : listEntityObj) {
			addCache(obj);
		}

	}

}
