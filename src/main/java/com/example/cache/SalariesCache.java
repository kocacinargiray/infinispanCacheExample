package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.CacheListener;
import com.example.model.Salaries;
import com.example.model.repository.SalariesRepository;

@Component
public class SalariesCache extends CacheImplementation {

	@Autowired
	SalariesRepository repository;

	Cache<String, Salaries> salaryCache;

	@Autowired
	public SalariesCache(CacheConfig config) {
		salaryCache = config.cacheManager.getCache("salaryCache");
		salaryCache.addListener(new CacheListener());
	}

	@Override
	public void addCache(Object entity) {
		Salaries entityObject = new Salaries();
		entityObject = (Salaries) entity;
		if (!salaryCache.containsKey(entityObject.cp.toString())) {
			salaryCache.put(entityObject.cp.toString(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		Salaries entityObject = new Salaries();
		entityObject = (Salaries) entity;
		salaryCache.put(entityObject.cp.toString(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		Salaries entityObject = new Salaries();
		entityObject = (Salaries) entity;
		salaryCache.remove(entityObject.cp.toString().toString());

	}

	@Override
	public Cache<String, Salaries> getCache() {
		return salaryCache;

	}

	@Override
	public Salaries findEntity(Object entity) {
		Salaries entityObject = (Salaries) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<Salaries> listEntityObj = repository.findAll();

		for (Salaries obj : listEntityObj) {
			addCache(obj);
		}

	}
}
