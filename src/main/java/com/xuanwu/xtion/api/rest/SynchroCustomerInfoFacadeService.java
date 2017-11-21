package com.xuanwu.xtion.api.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.xuanwu.xtion.domain.CustomerInfo;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.QueryParameters;
import com.xuanwu.xtion.service.CustomerInfoService;
/**
 * @Discription 客户信息同步
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-25
 *
 */
@RestController
public class SynchroCustomerInfoFacadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	
	@Autowired
	private CustomerInfoService cusInfoService;
	
	/**
	 * 同步客户信息
	 * @param request
	 * @param customerInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api/xtion/customerInfo",method=RequestMethod.POST)
	public JsonResp addCusInfo(HttpServletRequest request,@RequestBody CustomerInfo customerInfo){
		logger.debug(request.getRequestURL()+":"+JSONArray.toJSONString(customerInfo));
		JsonResp jsonResp =  JsonResp.failure("同步客户信息失败");
		try {
			//首先判断客户信息是否存在
			QueryParameters params = new QueryParameters();
			params.addParam("ecCode", customerInfo.getEcCode());
			params.addParam("ecName", customerInfo.getEcName());
			CustomerInfo cusInfo = cusInfoService.findCustInfoByNameAndCode(params);
			if(cusInfo != null){
				return JsonResp.failure("处理失败：客户信息已存在！");
			}
			Date curDate = new Date();
			customerInfo.setIsOpenStatus(1);
			customerInfo.setCreateTime(curDate);
			customerInfo.setUpdateTime(curDate);
			customerInfo.setEcStatus(1); //默认客户状态为正常
			boolean ret = cusInfoService.insertCusInfo(customerInfo)>0;
			if(ret){jsonResp=JsonResp.success("同步客户信息成功");}
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}
	
	/***
	 * 更新客户信息
	 * @param request
	 * @param customerInfo
	 * @return
	 */
	@RequestMapping(value="api/xtion/customerInfo",method=RequestMethod.PUT)
	public JsonResp updateCusInfo(HttpServletRequest request,@RequestBody CustomerInfo customerInfo){
		JsonResp jsonResp =  JsonResp.failure("更新客户信息失败");
		try {
			Date curDate = new Date();
			customerInfo.setUpdateTime(curDate);
			boolean ret = cusInfoService.updateCustomerInfo(customerInfo)>0;
			if(ret){jsonResp=JsonResp.success("更新客户信息成功");}
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}
	
	/**
	 * 根据id查询客户信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="api/xtion/customerInfo/{id}",method=RequestMethod.GET)
	public JsonResp selectCusInfo(HttpServletRequest request,@PathVariable("id")Integer id){
		JsonResp jsonResp =  JsonResp.failure("查询客户信息失败");
		try {
			CustomerInfo customerInfo = cusInfoService.findCustomerInfoByID(id);
			Map<String,Object> respMap = new HashMap<String,Object>();
			respMap.put("customerInfo", customerInfo);
			jsonResp=JsonResp.success(respMap);
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}
	
	/**
	 * 查询客户信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="api/xtion/customerInfos",method=RequestMethod.GET)
	public JsonResp selectCusInfos(HttpServletRequest request){
		JsonResp jsonResp =  JsonResp.failure("查询客户信息列表失败");
		try {
			List<CustomerInfo> customerInfos = cusInfoService.findCustomerInfos();
			Map<String,Object> respMap = new HashMap<String,Object>();
			respMap.put("customerInfos", customerInfos);
			jsonResp=JsonResp.success(respMap);
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}
	/**
	 * 删除客户信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="api/xtion/customerInfo/{id}",method=RequestMethod.DELETE)
	public JsonResp deleteCusInfo(HttpServletRequest request,@PathVariable("id")Integer id){
		JsonResp jsonResp =  JsonResp.failure("删除客户信息失败");
		try {
			boolean ret  = cusInfoService.deleteCustInfoById(id)>0;
			if(ret){jsonResp=JsonResp.success("客户信息删除成功");}
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}
	

}
