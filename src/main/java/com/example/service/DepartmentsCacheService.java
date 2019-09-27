package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.DepartmentsCache;
import com.example.model.Departments;

@RestController
public class DepartmentsCacheService implements CacheServiceInterface {

	@Autowired
	DepartmentsCache deptCache;

	@Override
	@PostMapping(value = "/getDeptCache")
	public Object getCache(@RequestBody String key) throws Exception {
		Departments department = new Departments();
		department = deptCache.getCache().get(Utility.toJson(key).getString("dept_no"));
		return department;
	}

	@Override
	@PostMapping(value = "/addDeptCache")
	public void addCache(@RequestBody String entity) {
		deptCache.addCache(Utility.fromJsonToObject(entity, Departments.class));
	}

	@Override
	@PostMapping(value = "/updateDeptCache")
	public void updateCache(@RequestBody String entity) {
		deptCache.updateCache(Utility.fromJsonToObject(entity, Departments.class));
	}

	@Override
	@PostMapping(value = "/removeDeptCache")
	public void removeCache(@RequestBody String entity) {
		deptCache.deleteCache(Utility.fromJsonToObject(entity, Departments.class));
	}
}
