package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cacheService")
public interface CacheServiceInterface {

	public Object getCache(String key) throws Exception;

	public void addCache(String entity);

	public void updateCache(String entity);

	public void removeCache(String entity);
}
