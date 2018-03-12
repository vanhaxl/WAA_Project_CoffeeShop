package edu.mum.coffee.restclient;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Identity;

public class RestClient<T extends Identity> {
	private String baseUrl;
	private Class<T> clazz;
	private Class<T[]> arrayClazz;
	private String entityName;
	
	@SuppressWarnings("unchecked")
	public RestClient(String baseUrl, Class<T> clazz) {
		this.baseUrl = baseUrl;
		this.clazz = clazz;
		this.arrayClazz = (Class<T[]>) java.lang.reflect.Array.newInstance(clazz, 0).getClass();		
		this.entityName = getClassName(clazz);
	}

	private String getClassName(Class<T> clazz) {
		String className = clazz.getName().toLowerCase();
		String[] names = className.split("\\.");
		return names[names.length - 1] + "s";
	}
	
	public T[] getAll() {
		RestTemplate restTemplate = new RestTemplate();
		T[] entities = restTemplate.getForObject(baseUrl + entityName, arrayClazz);		
		return entities;
	}
	
	public T getById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
		T entity = restTemplate.getForObject(baseUrl + entityName+ "/{id}", clazz, params);		
		return entity;
	}
	
	public T add(T entity) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<T> requestBody = new HttpEntity<>(entity, headers);
		return restTemplate.postForObject(baseUrl + entityName, requestBody, clazz);
	}
	
	public void update(T entity) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(entity.getId()));
	    RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<T> requestBody = new HttpEntity<>(entity, headers);
	    restTemplate.put (baseUrl + entityName + "/{id}", requestBody, params);
	}
	
	public void delete(T entity) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(entity.getId()));
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete(baseUrl + entityName + "/{id}", params);
	}
}
