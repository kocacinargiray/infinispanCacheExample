package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.DepartmentManagerCacheListener;
import com.example.model.DepartmentManager;
import com.example.model.repository.DepartmentManagerRepository;

@Component
public class DepartmentManagerCache extends CacheImplementation {

	@Autowired
	DepartmentManagerRepository repository;

	Cache<String, DepartmentManager> deptMngCache;

	@Autowired
	public DepartmentManagerCache(CacheConfig config, DepartmentManagerCacheListener listener) {
		deptMngCache = config.cacheManager.getCache("deptManegerCache");
		deptMngCache.addListener(listener);
	}

	@Override
	public void addCache(Object entity) {
		DepartmentManager entityObject = new DepartmentManager();
		entityObject = (DepartmentManager) entity;

		if (!deptMngCache.containsKey(entityObject.cp.toString())) {
			deptMngCache.put(entityObject.cp.toString(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		DepartmentManager entityObject = new DepartmentManager();
		entityObject = (DepartmentManager) entity;
		deptMngCache.put(entityObject.cp.toString(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		DepartmentManager entityObject = new DepartmentManager();
		entityObject = (DepartmentManager) entity;
		deptMngCache.remove(entityObject.cp.toString());

	}

	@Override
	public Cache<String, DepartmentManager> getCache() {
		return deptMngCache;

	}

	@Override
	public DepartmentManager findEntity(Object entity) {
		DepartmentManager entityObject = (DepartmentManager) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<DepartmentManager> listEntityObj = repository.findAll();

		for (DepartmentManager obj : listEntityObj) {
			addCache(obj);
		}

	}

}
