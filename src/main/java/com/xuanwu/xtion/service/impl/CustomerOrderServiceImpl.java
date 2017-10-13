package com.xuanwu.xtion.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanwu.xtion.dao.CustomerOrderDao;
import com.xuanwu.xtion.domain.CusFeeItemsList;
import com.xuanwu.xtion.domain.CustomerOrder;
import com.xuanwu.xtion.domain.QueryParameters;
import com.xuanwu.xtion.service.CustomerOrderService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderServiceImpl.class);
	@Autowired
	private CustomerOrderDao cusOrderDao;
	/**
	 * 根据订单id查询出资费信息列表
	 */
	@Override
	public List<CusFeeItemsList> findFeeListByOrderId(int cusOrderId) {
		return cusOrderDao.findFeeListByOrderId(cusOrderId);
	}
	/**
	 * 新增订单信息
	 */
	@Override
	@Transactional
	public int addCustomerOrder(CustomerOrder custOrder)throws Exception {
		int ret = 0; 
		cusOrderDao.addCustomerOrder(custOrder);
		int cusOrderId = custOrder.getId();
		CusFeeItemsList[] feeItems = custOrder.feeProList;
		for(CusFeeItemsList o:feeItems){
			o.setCusOrderId(cusOrderId);
			ret = cusOrderDao.addCusFeeItemsList(o);
		}
		return ret;
	}
	/***
	 * 新增资费项目信息
	 */
	@Override
	public int addCusFeeItemsList(CusFeeItemsList feeItemsList) {
		return cusOrderDao.addCusFeeItemsList(feeItemsList);
	}
	/***
	 * 查询产品订购信息是否已存在
	 */
	@Override
	public CustomerOrder findOrderInfoByorderIdAndEcname(QueryParameters params) {
		return cusOrderDao.findOrderInfoByorderIdAndEcname(params);
	}
	/**
	 * 更新订单消息
	 */
	@Override
	public int updateCustomerOrder(CustomerOrder custOrder) {
		return cusOrderDao.updateCustomerOrder(custOrder);
	}
	/**
	 * 更新项目资费列表
	 */
	@Override
	public int updateCusFeeItemsList(CusFeeItemsList feeList) {
		return cusOrderDao.updateCusFeeItemsList(feeList);
	}
	/***
	 * 根据id查询订单信息
	 */
	@Override
	public CustomerOrder findOrderInfoById(int id) {
		return cusOrderDao.findOrderInfoById(id);
	}
	

}
