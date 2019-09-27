package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.SalariesCache;
import com.example.model.Salaries;
import com.example.model.SalariesCompositeKeys;

@RestController
public class SalariesCacheService implements CacheServiceInterface {

	@Autowired
	SalariesCache salaryCache;

	@Override
	@PostMapping("/getSalaryCache")
	public Object getCache(@RequestBody String key) throws Exception {
		SalariesCompositeKeys salaryKeys = new SalariesCompositeKeys();
		salaryKeys = salaryKeys.getFromJson(Utility.toJson(key));
		Salaries salary = new Salaries();
		salary = salaryCache.getCache().get(salaryKeys.toString());
		return salary;
	}

	@Override
	@PostMapping(value = "/addSalaryCache")
	public void addCache(@RequestBody String entity) {
		salaryCache.addCache(Utility.fromJsonToObject(entity, Salaries.class));
	}

	@Override
	@PostMapping(value = "/updateSalaryCache")
	public void updateCache(@RequestBody String entity) {
		salaryCache.updateCache(Utility.fromJsonToObject(entity, Salaries.class));
	}

	@Override
	@PostMapping(value = "/removeSalaryCache")
	public void removeCache(@RequestBody String entity) {
		salaryCache.deleteCache(Utility.fromJsonToObject(entity, Salaries.class));
	}
}
