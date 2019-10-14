package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.DepartmentCacheListener;
import com.example.model.Departments;
import com.example.model.repository.DepartmentsRepository;

@Component
public class DepartmentsCache extends CacheImplementation {

	@Autowired
	DepartmentsRepository repository;

	Cache<String, Departments> deptCache;

	@Autowired
	public DepartmentsCache(CacheConfig config, DepartmentCacheListener listener) {
		deptCache = config.cacheManager.getCache("deptCache");
		deptCache.addListener(listener);
	}

	@Override
	public void addCache(Object entity) {
		Departments entityObject = new Departments();
		entityObject = (Departments) entity;

		if (!deptCache.containsKey(entityObject.getDept_no())) {
			deptCache.put(entityObject.getDept_no(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		Departments entityObject = new Departments();
		entityObject = (Departments) entity;
		deptCache.put(entityObject.getDept_no(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		Departments entityObject = new Departments();
		entityObject = (Departments) entity;
		deptCache.remove(entityObject.getDept_no());

	}

	@Override
	public Cache<String, Departments> getCache() {
		return deptCache;

	}

	@Override
	public Departments findEntity(Object entity) {
		Departments entityObject = (Departments) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<Departments> listEntityObj = repository.findAll();

		for (Departments obj : listEntityObj) {
			addCache(obj);
		}

	}

}
