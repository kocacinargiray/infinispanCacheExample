package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.EmployeeCache;
import com.example.model.Employees;

@RestController
public class EmployeeCacheService implements CacheServiceInterface {

	@Autowired
	EmployeeCache empCache;

	@Override
	@PostMapping("/getEmpCache")
	public Object getCache(@RequestBody String key) throws Exception {
		Employees employee = new Employees();
		employee = empCache.getCache().get(Utility.toJson(key).getInt("emp_no"));
		return employee;
	}

	@Override
	@PostMapping(value = "/addEmpCache")
	public void addCache(@RequestBody String entity) {
		empCache.addCache(Utility.fromJsonToObject(entity, Employees.class));
	}

	@Override
	@PostMapping(value = "/updateEmpCache")
	public void updateCache(@RequestBody String entity) {
		empCache.updateCache(Utility.fromJsonToObject(entity, Employees.class));
	}

	@Override
	@PostMapping(value = "/removeEmpCache")
	public void removeCache(@RequestBody String entity) {
		empCache.deleteCache(Utility.fromJsonToObject(entity, Employees.class));
	}
}
