package com.example.cache.Listener;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

import com.example.Utility;

@Listener(clustered = true)
public class CacheListener {

	@PersistenceContext
	private EntityManager emt;

	@CacheEntryCreated
	public void CacheEntryCreatedListener(CacheEntryCreatedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				emt = Utility.getEntityManager();
				Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(event.getValue());
				Class<? extends Object> objectClass = Class.forName(
						"com.example.model.repository." + event.getValue().getClass().getSimpleName() + "Repository");
				Method method = objectClass.getMethod("findById", Object.class);
				Object object = method.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), id);

				if (object == null) {
					objectClass.getMethod("save", Object.class)
							.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), event.getValue());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@CacheEntryModified
	public void CacheEntryModifiedListener(CacheEntryModifiedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				emt = Utility.getEntityManager();
				Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(event.getValue());
				Class<? extends Object> objectClass = Class.forName(
						"com.example.model.repository." + event.getValue().getClass().getSimpleName() + "Repository");
				Method method = objectClass.getMethod("findById", Object.class);
				Object object = method.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), id);

				if (object != null) {
					objectClass.getMethod("update", Object.class)
							.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), event.getValue());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@CacheEntryRemoved
	public void CacheEntryRemovedListener(CacheEntryRemovedEvent<?, ?> event) {
		if (!event.isPre()) {
			try {
				emt = Utility.getEntityManager();
				Object id = emt.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(event.getOldValue());
				Class<? extends Object> objectClass = Class.forName("com.example.model.repository."
						+ event.getOldValue().getClass().getSimpleName() + "Repository");
				Method method = objectClass.getMethod("findById", Object.class);
				Object object = method.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), id);

				if (object != null) {
					objectClass.getMethod("delete", Object.class)
							.invoke(objectClass.getConstructor(EntityManager.class).newInstance(emt), id);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
