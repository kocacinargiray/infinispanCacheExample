package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.DepartmentManagerCache;
import com.example.model.DepartmentManager;
import com.example.model.DepartmentsCompositeKeys;

@RestController
public class DepartmentManagerCacheService implements CacheServiceInterface {

	@Autowired
	DepartmentManagerCache deptMngCache;

	@Override
	@PostMapping("/getDeptMngCache")
	public Object getCache(@RequestBody String key) throws Exception {
		DepartmentsCompositeKeys deptKeys = new DepartmentsCompositeKeys();
		deptKeys = deptKeys.getFromJson(Utility.toJson(key));
		DepartmentManager departmentMng = new DepartmentManager();
		departmentMng = deptMngCache.getCache().get(deptKeys.toString());
		return departmentMng;
	}

	@Override
	@PostMapping(value = "/addDeptMngCache")
	public void addCache(@RequestBody String entity) {
		deptMngCache.addCache(Utility.fromJsonToObject(entity, DepartmentManager.class));
	}

	@Override
	@PostMapping(value = "/updateDeptMngCache")
	public void updateCache(@RequestBody String entity) {
		deptMngCache.updateCache(Utility.fromJsonToObject(entity, DepartmentManager.class));
	}

	@Override
	@PostMapping(value = "/removeDeptMngCache")
	public void removeCache(@RequestBody String entity) {
		deptMngCache.deleteCache(Utility.fromJsonToObject(entity, DepartmentManager.class));
	}
}
