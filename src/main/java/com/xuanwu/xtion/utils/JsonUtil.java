package com.xuanwu.xtion.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	/**
	 * 
	 * 自定义时间格式
	 * 
	 */
	private static DateFormat format;
	/**
	 * 
	 * ObjectMapper对象
	 * 
	 */
	private static ObjectMapper objectMapper;

	/**
	 * 
	 * 初始化
	 * 
	 */
	static {
		format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(format);
	}

	public static <T> String getJson(T object) {

		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T getObject(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> getList(String json, Class<T> clazz) {
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(json, javaType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
