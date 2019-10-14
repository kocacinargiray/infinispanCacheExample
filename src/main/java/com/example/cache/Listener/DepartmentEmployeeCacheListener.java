package com.example.cache.Listener;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.repository.DepartmentEmployeeRepository;

@Listener(clustered = true)
@Component
public class DepartmentEmployeeCacheListener implements CacheListenerInterface {

	@Autowired
	DepartmentEmployeeRepository repository;

	@Override
	@CacheEntryCreated
	public void CacheEntryCreatedListener(CacheEntryCreatedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				Object id = repository.findId(event.getValue());
				Object object = repository.findById(id);
				if (object == null) {
					repository.save(event.getValue());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	@CacheEntryModified
	public void CacheEntryModifiedListener(CacheEntryModifiedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				Object id = repository.findId(event.getValue());
				Object object = repository.findById(id);
				if (object != null) {
					repository.update(event.getValue());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	@CacheEntryRemoved
	public void CacheEntryRemovedListener(CacheEntryRemovedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				Object id = repository.findId(event.getOldValue());
				Object object = repository.findById(id);
				if (object != null) {
					repository.delete(id);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
