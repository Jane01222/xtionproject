package com.xuanwu.xtion.utils;


import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Http客户端请求工具类
 *
 * @author ttan
 * @date 2017-1-16
 */
public final class HttpClientUtil {
    /**
     * 表头信息
     */
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=UTF-8";

    // 默认构造方法
    private HttpClientUtil() {

    }

    /**
     * 公用的Post方法
     * 
     * @param url
     * @param paramMap
     * @param clazz
     * @return
     */
    public static <T> T doPost(String url, Map<String, Object> paramMap, Class<T> clazz) {
        ClientResponse clientResponse = doPost(url, paramMap);
        return clientResponse.getEntity(clazz);
    }

    /**
     * 公用的Post方法
     *
     * @param url
     * @param paramMap
     * @param clazz
     * @return
     */
    public static <T> T doPut(String url, Map<String, Object> paramMap, Class<T> clazz) {
        ClientResponse clientResponse = doPut(url, paramMap);
        return clientResponse.getEntity(clazz);
    }

    /**
     * 返回ClientResponse
     * 
     * @param url
     * @param paramMap
     * @return
     */
    private static ClientResponse doPost(String url, Map<String, Object> paramMap) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse result = webResource.header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                                           .type(MediaType.APPLICATION_JSON)
                                           .post(ClientResponse.class, JsonUtil.getJson(paramMap));
        return result;
    }

    /**
     * 返回ClientResponse
     *
     * @param url
     * @return
     */
	@SuppressWarnings("unused")
	private static ClientResponse doGet(String url) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse result = webResource.header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                                           .type(MediaType.APPLICATION_JSON)
                                           .get(ClientResponse.class);
        return result;
    }

    /**
     * 返回ClientResponse
     *
     * @param url
     * @param paramMap
     * @return
     */
    private static ClientResponse doPut(String url, Map<String, Object> paramMap) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse result = webResource.header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                                           .type(MediaType.APPLICATION_JSON)
                                           .put(ClientResponse.class, paramMap);
        return result;
    }

}
