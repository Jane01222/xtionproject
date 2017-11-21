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
import com.xuanwu.xtion.domain.CustomerOrder;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.QueryParameters;
import com.xuanwu.xtion.service.CustomerOrderService;
import com.xuanwu.xtion.utils.OrderConfirmTask;
import com.xuanwu.xtion.utils.SingleThreadPoolUtil;

/**
 * @Discription 产品订购通知接口
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-31
 */
@RestController
public class ProductOderNotifyFacadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	
	@Autowired
	private CustomerOrderService cusOrderService;
	
	@Autowired
	private ProductOrderConfirmFacadeService productOrderConfirmFacadeService;
	
	/**
	 * 新增产品订购订单消息
	 * @param request
	 * @param customerOrder
	 * @return
	 */
	@RequestMapping(value="api/xtion/order",method = RequestMethod.POST)
	public JsonResp orderProductNoti(HttpServletRequest request,@RequestBody CustomerOrder customerOrder){
		logger.debug(request.getRequestURL()+":"+JSONArray.toJSONString(customerOrder));
		JsonResp jsonResp = JsonResp.failure("产品订购信息通知失败");
		try {
			QueryParameters params = new QueryParameters();
			params.addParam("ecCode", customerOrder.getEcCode());
			params.addParam("orderId", customerOrder.getOrderId());
			CustomerOrder cOrder = cusOrderService.findOrderInfoByorderIdAndEcname(params);
			if(cOrder!=null){
				return JsonResp.failure("产品订购信息已存在，不能重复通知");
			}
			Date curDate = new Date();
			Date orderTime = new Date(customerOrder.getOrderTime());
			customerOrder.setCreateTime(curDate);
			customerOrder.setUpdateTime(curDate);
			customerOrder.setOrderdTime(orderTime); //利用orderdTime做中间变量实现日期转换
			boolean ret = cusOrderService.addCustomerOrder(customerOrder)>0;
			if(ret){
				jsonResp = JsonResp.success("产品订购信息通知成功");
				//TODO 产品订单插入成功之后调用客户的订单确认接口
				SingleThreadPoolUtil.putInPool(new OrderConfirmTask(customerOrder.getId(), productOrderConfirmFacadeService));
			}
		} catch (Exception e) {
			logger.error("orderProductNotify error cause by", e);
		}
		return jsonResp;
	}
	
	/***
	 * 修改产品订购消息
	 * @param request
	 * @param customerOrder
	 * @return
	 */
	@RequestMapping(value="api/xtion/porder",method = RequestMethod.PUT)
	public JsonResp updateOrderProductNoti(HttpServletRequest request,@RequestBody CustomerOrder customerOrder){
		JsonResp jsonResp = JsonResp.failure("修改产品订购信息失败");
		try {
			Date curDate = new Date();
			customerOrder.setUpdateTime(curDate);
			boolean ret = cusOrderService.updateCustomerOrder(customerOrder)>0;
			if(ret){jsonResp = JsonResp.success("产品订购信息修改成功");}
		} catch (Exception e) {
			logger.error("updateOrderProductNoti error cause by", e);
		}
		return jsonResp;
	}
	


}
