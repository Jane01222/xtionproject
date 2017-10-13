package com.xuanwu.xtion.utils;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.xuanwu.xtion.api.rest.SynchroCustomerInfoFacadeService;
import com.xuanwu.xtion.domain.JsonResp;

/**
 * @Description token验证工具类
 * @author Chunyan.Liu
 * @Date 2017-09-06
 *
 */
public class TokenVerifyUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	
	public static JsonResp tokenVerification(String token){
		JsonResp jsonResp = JsonResp.failure("token验证失败");
		try {
			Client client = Client.create();
			String tokenUri = PropertiesUtil.getPropertyValue("param.properties", "tokenVerifyUri");
			tokenUri = new StringBuilder().append(tokenUri).append("?token=").append(token).toString();
	        WebResource webResource = client.resource(tokenUri);
	        ClientResponse result = webResource.header("Content-Type", "application/json;charset=UTF-8")
	                                           .type(MediaType.APPLICATION_JSON)
	                                           .get(ClientResponse.class);
	        jsonResp = JsonResp.success(result.getEntity(String.class));
		} catch (Exception e) {
			logger.error("tokenVerification error caused by", e);
		}
		return jsonResp;
	}
}
