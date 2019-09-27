package com.example.cache;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.ClusterListener;

@Component
public class CacheConfig {

	DefaultCacheManager cacheManager;

	public CacheConfig() {
		GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
		global.transport().clusterName("Company");
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.clustering().cacheMode(CacheMode.DIST_SYNC);
		cacheManager = new DefaultCacheManager(global.build(), builder.build());
		cacheManager.addListener(new ClusterListener());
	}
}
