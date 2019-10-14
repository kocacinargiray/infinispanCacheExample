package com.example.cache.Listener;

import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

public interface CacheListenerInterface {

	public void CacheEntryCreatedListener(CacheEntryCreatedEvent<?, ?> event);

	public void CacheEntryModifiedListener(CacheEntryModifiedEvent<?, ?> event);

	public void CacheEntryRemovedListener(CacheEntryRemovedEvent<?, ?> event);
}
