package com.xuanwu.xtion.api.rest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.xuanwu.xtion.domain.CustomerInfo;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.QueryParameters;
import com.xuanwu.xtion.service.CustomerInfoService;

/**
 * @Decription 客户状态同步API
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-28
 *
 */
@RestController
public class SynchroCustomerStatusFacadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	
	@Autowired
	private CustomerInfoService cusInfoService;
	
	@RequestMapping(value="api/xtion/customerStatus",method=RequestMethod.POST)
	public JsonResp updateCusStatus(HttpServletRequest request,@RequestBody CustomerInfo customerInfo){
		logger.debug(request.getRequestURL()+":"+JSONArray.toJSONString(customerInfo));
		JsonResp jsonResp =  JsonResp.failure("更新客户状态失败");
		try {
			Integer ecStatus = customerInfo.getEcStatus();
			QueryParameters params = new QueryParameters();
			params.addParam("ecName", customerInfo.getEcName());
			params.addParam("ecCode", customerInfo.getEcCode());
			customerInfo = cusInfoService.findCustInfoByNameAndCode(params); //根据客户名称和客户编号查询出客户信息
			Date curDate = new Date();
			customerInfo.setUpdateTime(curDate);
			customerInfo.setEcStatus(ecStatus);
			boolean ret = cusInfoService.updateCustomerInfo(customerInfo)>0;
			if(ret){jsonResp=JsonResp.success("更新客户状态成功");}
		} catch (Exception e) {
			logger.error("add customerInfo error caused by:",e);
		}
		return jsonResp;
	}

}
