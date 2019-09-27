package com.example;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cache.DepartmentEmployeeCache;
import com.example.cache.DepartmentManagerCache;
import com.example.cache.DepartmentsCache;
import com.example.cache.EmployeeCache;
import com.example.cache.SalariesCache;
import com.example.cache.TitleCache;

@Component
public class CacheInitializer implements InitializingBean {

	@Autowired
	DepartmentEmployeeCache deptEmpCache;

	@Autowired
	DepartmentManagerCache deptMngCache;

	@Autowired
	DepartmentsCache deptCache;

	@Autowired
	EmployeeCache empCache;

	@Autowired
	SalariesCache salaryCache;

	@Autowired
	TitleCache titleCache;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init() {
		deptEmpCache.initializeCache();
		deptMngCache.initializeCache();
		deptCache.initializeCache();
		empCache.initializeCache();
		salaryCache.initializeCache();
		titleCache.initializeCache();
	}

}
