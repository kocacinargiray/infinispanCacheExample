package com.example.cache;

import java.util.concurrent.TimeUnit;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.springframework.stereotype.Component;

import com.example.cache.Listener.ClusterListener;
import com.example.model.DepartmentEmployee;
import com.example.model.DepartmentManager;
import com.example.model.Salaries;
import com.example.model.Titles;

@Component
public class CacheConfig {

	DefaultCacheManager cacheManager;

	public CacheConfig() {
		GlobalConfigurationBuilder global = GlobalConfigurationBuilder.defaultClusteredBuilder();
		global.transport().clusterName("Company").addProperty("configurationFile", "default-jgroups-kubernetes.xml")
				.initialClusterTimeout(30000, TimeUnit.MILLISECONDS)
				.distributedSyncTimeout(60000, TimeUnit.MILLISECONDS).serialization()
				.addAdvancedExternalizer(new DepartmentEmployee.DeptEmpExternalizer())
				.addAdvancedExternalizer(new DepartmentManager.DeptMngExternalizer())
				.addAdvancedExternalizer(new Salaries.SalaryExternalizer())
				.addAdvancedExternalizer(new Titles.TitleExternalizer());

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.clustering().cacheMode(CacheMode.DIST_SYNC).hash().numOwners(1);
		cacheManager = new DefaultCacheManager(global.build(), builder.build());
		cacheManager.addListener(new ClusterListener());

		System.out.println(cacheManager.getTransport().getAddress().toString());
		System.out.println(cacheManager.getMembers().toString());
		System.out.println(cacheManager.getCoordinatorAddress());
	}
}
