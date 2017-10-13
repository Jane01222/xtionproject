package com.xuanwu.xtion.utils;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

public class HttpUtil {
	public static <T> T post(String uri, String resource, MultivaluedMap<String, Object> headers,
			Map<String, Object> params, Class<T> clazz) {
		Client client = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
		WebTarget target = client.target(uri).path(resource);
		return target.request(MediaType.APPLICATION_JSON).headers(headers)
				.post(Entity.entity(params, MediaType.APPLICATION_JSON), clazz);
	}

	public static <T> T post(String url, MultivaluedMap<String, Object> headers, Map<String, Object> params,
			Class<T> clazz) {
		Client client = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
		WebTarget target = client.target(url);
		return target.request(MediaType.APPLICATION_JSON).headers(headers)
				.post(Entity.entity(params, MediaType.APPLICATION_JSON), clazz);
	}

	public static Response post(String url, MultivaluedMap<String, Object> headers, Map<String, Object> params) {
		Client client = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
		WebTarget target = client.target(url);
		return target.request(MediaType.APPLICATION_JSON).headers(headers)
				.post(Entity.entity(params, MediaType.APPLICATION_JSON));
	}

	public static Response put(String url, MultivaluedMap<String, Object> headers, Map<String, Object> params) {
		Client client = ClientBuilder.newClient().register(JacksonJaxbJsonProvider.class);
		WebTarget target = client.target(url);
		return target.request(MediaType.APPLICATION_JSON).headers(headers)
				.put(Entity.entity(params, MediaType.APPLICATION_JSON));
	}
}
