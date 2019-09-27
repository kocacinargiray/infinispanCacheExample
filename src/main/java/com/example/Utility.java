package com.example;

import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONObject;

import com.google.gson.Gson;

public class Utility {

	private static final EntityManagerFactory emFactoryObj;
	private static final String PERSISTENCE_UNIT_NAME = "Employees";

	static {
		emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// This Method Is Used To Retrieve The 'EntityManager' Object
	public static EntityManager getEntityManager() {
		return emFactoryObj.createEntityManager();
	}

	public static JSONObject toJson(String jsonString) {
		JSONObject json = new JSONObject(jsonString);
		return json;
	}

	public static Object fromJsonToObject(String json, Type type) {
		Object object = new Gson().fromJson(json, type);
		return object;
	}
}
