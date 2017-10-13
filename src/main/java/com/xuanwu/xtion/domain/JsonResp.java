/*
 * Copyright (c) 2015 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package com.xuanwu.xtion.domain;

import com.xuanwu.xtion.utils.Assert;



/**
 * @Description 通用JSON处理结果
 * @author <a href="mailto:liushuaiying@139130.net">Shuaiying.Liu</a>
 * @Data 2015年4月27日
 * @Version 1.0.0
 */
public class JsonResp {

	public static final String SUCCESS_RESULTCODE = "200"; //处理成功编号
	
	public static final String DEFAULT_FAIL_RESULTCODE = "400"; //处理失败编号
	
	private String resultCode;
	
	private String resultMsg;
	
	private Object data;
	
	public JsonResp(String resultCode, String resultMsg,Object data) {
		this.resultCode = resultCode;
		this.data = data;
		this.resultMsg = resultMsg;
	}
	
	public JsonResp(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
	public JsonResp(String resultCode, Object data) {
		this.resultCode = resultCode;
		this.data = data;
	}
	
	public static JsonResp success(){
		return new JsonResp(SUCCESS_RESULTCODE, null);
	}
	
	public static JsonResp success(Object data){
		return new JsonResp(SUCCESS_RESULTCODE, data);
	}
	
	public static JsonResp success(String resultMsg){
		return new JsonResp(SUCCESS_RESULTCODE,resultMsg);
	}
	
	public static JsonResp success(String resultCode,String resultMsg){
		return new JsonResp(SUCCESS_RESULTCODE,resultMsg);
	}
	
	public static JsonResp failure(String resultMsg){
		return new JsonResp(DEFAULT_FAIL_RESULTCODE,resultMsg);
	}
	
	public static JsonResp fail(String resultCode, String errorMsg){
		Assert.isTrue(resultCode != SUCCESS_RESULTCODE, "Must be not success status: " + resultCode);
		return new JsonResp(DEFAULT_FAIL_RESULTCODE, null, errorMsg);
	}

	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		return "JsonResp [resultCode=" + resultCode + ", resultMsg="
				+ resultMsg +  ", data=" + data
				+ "]";
	}
	
}
