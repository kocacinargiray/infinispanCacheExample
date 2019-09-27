package com.example.cache;

import org.infinispan.Cache;

public abstract class CacheImplementation {
	public abstract void addCache(Object entity);

	public abstract void initializeCache();

	public abstract void updateCache(Object entity);

	public abstract void deleteCache(Object entity);

	public abstract Cache<?, ?> getCache();

	public abstract Object findEntity(Object entity);
}
