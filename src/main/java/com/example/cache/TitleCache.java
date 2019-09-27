package com.example.cache;

import java.util.List;

import org.infinispan.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.CacheListener;
import com.example.model.Titles;
import com.example.model.repository.TitlesRepository;

@Component
public class TitleCache extends CacheImplementation {

	@Autowired
	TitlesRepository repository;

	Cache<String, Titles> titleCache;

	@Autowired
	public TitleCache(CacheConfig config) {
		titleCache = config.cacheManager.getCache("titleCache");
		titleCache.addListener(new CacheListener());
	}

	@Override
	public void addCache(Object entity) {
		Titles entityObject = new Titles();
		entityObject = (Titles) entity;

		if (!titleCache.containsKey(entityObject.cp.toString())) {
			titleCache.put(entityObject.cp.toString(), entityObject);
		}
	}

	@Override
	public void updateCache(Object entity) {
		Titles entityObject = new Titles();
		entityObject = (Titles) entity;
		titleCache.put(entityObject.cp.toString(), entityObject);
	}

	@Override
	public void deleteCache(Object entity) {
		Titles entityObject = new Titles();
		entityObject = (Titles) entity;
		titleCache.remove(entityObject.cp.toString());

	}

	@Override
	public Cache<String, Titles> getCache() {
		return titleCache;

	}

	@Override
	public Titles findEntity(Object entity) {
		Titles entityObject = (Titles) repository.findById(entity);
		return entityObject;
	}

	@Override
	public void initializeCache() {
		List<Titles> listEntityObj = repository.findAll();

		for (Titles obj : listEntityObj) {
			addCache(obj);
		}

	}
}
