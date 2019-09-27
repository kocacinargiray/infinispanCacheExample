package com.example.cache.Listener;

import java.util.HashSet;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStarted;
import org.infinispan.notifications.cachemanagerlistener.annotation.CacheStopped;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStoppedEvent;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;
import org.infinispan.remoting.transport.Address;

@Listener
public class ClusterListener {
	@CacheStarted
	public void cacheStarted(CacheStartedEvent event) {
		// Print the name of the Cache that started
		System.out.println("Cache Started: " + event.getCacheName());
	}

	@CacheStopped
	public void cacheStopped(CacheStoppedEvent event) {
		// Print the name of the Cache that stopped
		System.out.println("Cache Stopped: " + event.getCacheName());
	}

	@ViewChanged
	public void viewChanged(ViewChangedEvent event) {
		HashSet<Address> oldMembers = new HashSet<Address>(event.getOldMembers());
		HashSet<Address> newMembers = new HashSet<Address>(event.getNewMembers());
		@SuppressWarnings("unchecked")
		HashSet<Address> oldCopy = (HashSet<Address>) oldMembers.clone();

		// Remove all new nodes from the old view.
		// The resulting set indicates nodes that have left the cluster.
		oldCopy.removeAll(newMembers);
		if (oldCopy.size() > 0) {
			for (Address oldAdd : oldCopy) {
				System.out.println("Node left:" + oldAdd.toString());
			}
		}

		// Remove all old nodes from the new view.
		// The resulting set indicates nodes that have joined the cluster.
		newMembers.removeAll(oldMembers);
		if (newMembers.size() > 0) {
			for (Address newAdd : newMembers) {
				System.out.println("Node joined: " + newAdd.toString());
			}
		}
	}
}
