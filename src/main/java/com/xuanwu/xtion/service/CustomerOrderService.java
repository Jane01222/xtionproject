package com.xuanwu.xtion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuanwu.xtion.domain.CusFeeItemsList;
import com.xuanwu.xtion.domain.CustomerOrder;
import com.xuanwu.xtion.domain.QueryParameters;

@Service
public interface CustomerOrderService {
	/**根据订单ID查询自费项目信息*/
	public List<CusFeeItemsList> findFeeListByOrderId(int cusOrderId);
	/**新增客户订购订单消息
	 * @throws Exception */
	public int addCustomerOrder(CustomerOrder custOrder) throws Exception;
	/**新增资费项目信息*/
	public int addCusFeeItemsList(CusFeeItemsList feeItemsList);
	/**查询订单消息是否已存在*/
	public CustomerOrder findOrderInfoByorderIdAndEcname(QueryParameters params);
	/**更新订单消息*/
	public int updateCustomerOrder(CustomerOrder custOrder);
	/**更新资费信息*/
	public int updateCusFeeItemsList(CusFeeItemsList feeList);
	/**根据id查询订单信息*/
	public CustomerOrder findOrderInfoById( int id);

}
