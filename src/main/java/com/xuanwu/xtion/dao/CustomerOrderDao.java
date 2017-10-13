package com.xuanwu.xtion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanwu.xtion.domain.CusFeeItemsList;
import com.xuanwu.xtion.domain.CustomerOrder;
import com.xuanwu.xtion.domain.QueryParameters;


public interface CustomerOrderDao {
	/**根据订单ID查询自费项目信息*/
	public List<CusFeeItemsList> findFeeListByOrderId(@Param("cusOrderId") int cusOrderId);
	/**新增客户订购订单消息*/
	public int addCustomerOrder(CustomerOrder custOrder);
	/**新增资费项目信息*/
	public int addCusFeeItemsList(CusFeeItemsList feeItemsList);
	/**查询订单消息是否已存在*/
	public CustomerOrder findOrderInfoByorderIdAndEcname(QueryParameters params);
	/**更新订单消息*/
	public int updateCustomerOrder(CustomerOrder custOrder);
	/**更新资费信息*/
	public int updateCusFeeItemsList(CusFeeItemsList feeList);
	/**根据id查询订单信息*/
	public CustomerOrder findOrderInfoById(@Param("id") int id);
	
}
