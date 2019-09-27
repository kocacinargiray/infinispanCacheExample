package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.DepartmentEmployeeCache;
import com.example.model.DepartmentEmployee;
import com.example.model.DepartmentsCompositeKeys;

@RestController
public class DepartmentEmployeeCacheService implements CacheServiceInterface {

	@Autowired
	DepartmentEmployeeCache deptEmpCache;

	@Override
	@PostMapping("/getDeptEmpCache")
	public Object getCache(@RequestBody String key) throws Exception {
		DepartmentsCompositeKeys deptKeys = new DepartmentsCompositeKeys();
		deptKeys = deptKeys.getFromJson(Utility.toJson(key));
		DepartmentEmployee departmentEmp = new DepartmentEmployee();
		departmentEmp = deptEmpCache.getCache().get(deptKeys.toString());
		return departmentEmp;
	}

	@Override
	@PostMapping(value = "/addDeptEmpCache")
	public void addCache(@RequestBody String entity) {
		deptEmpCache.addCache(Utility.fromJsonToObject(entity, DepartmentEmployee.class));
	}

	@Override
	@PostMapping(value = "/updateDeptEmpCache")
	public void updateCache(@RequestBody String entity) {
		deptEmpCache.updateCache(Utility.fromJsonToObject(entity, DepartmentEmployee.class));
	}

	@Override
	@PostMapping(value = "/removeDeptEmpCache")
	public void removeCache(@RequestBody String entity) {
		deptEmpCache.deleteCache(Utility.fromJsonToObject(entity, DepartmentEmployee.class));
	}
}
