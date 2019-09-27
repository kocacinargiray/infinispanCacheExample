package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Utility;
import com.example.cache.TitleCache;
import com.example.model.TitleCompositeKey;
import com.example.model.Titles;

@RestController
public class TitleCacheService implements CacheServiceInterface {

	@Autowired
	TitleCache titleCache;

	@Override
	@PostMapping("/getTitleCache")
	public Object getCache(@RequestBody String key) throws Exception {
		TitleCompositeKey titleKeys = new TitleCompositeKey();
		titleKeys = titleKeys.getFromJson(Utility.toJson(key));
		Titles title = new Titles();
		title = titleCache.getCache().get(titleKeys.toString());
		return title;
	}

	@Override
	@PostMapping(value = "/addTitleCache")
	public void addCache(@RequestBody String entity) {
		titleCache.addCache(Utility.fromJsonToObject(entity, Titles.class));
	}

	@Override
	@PostMapping(value = "/updateTitleCache")
	public void updateCache(@RequestBody String entity) {
		titleCache.updateCache(Utility.fromJsonToObject(entity, Titles.class));
	}

	@Override
	@PostMapping(value = "/removeTitleCache")
	public void removeCache(@RequestBody String entity) {
		titleCache.deleteCache(Utility.fromJsonToObject(entity, Titles.class));
	}
}
