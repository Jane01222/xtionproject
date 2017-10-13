package com.xuanwu.xtion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanwu.xtion.domain.AccountManager;
import com.xuanwu.xtion.domain.CustomerInfo;
import com.xuanwu.xtion.domain.KeyPersonInfo;
import com.xuanwu.xtion.domain.QueryParameters;


public interface CustomerInfoDao {
	/**新增客户信息*/
	public int insertCusInfo(CustomerInfo customerInfo);
	/**更新客户信息*/
	public int updateCustomerInfo(CustomerInfo customerInfo);
	/**查询客户信息*/
	public CustomerInfo findCustomerInfoByID(@Param("id")int id);
	/**查询客户信息*/
	public List<CustomerInfo> findCustomerInfos();
	/**删除客户信息*/
	public int deleteCustInfoById(@Param("id")int id);
	/**查询客户经理信息*/
	public List<AccountManager> findAccountManagerByCustId(@Param("customerId")int customerId);
	/**查询关键人信息*/
	public List<KeyPersonInfo> findKeyPerinfoByCustId(@Param("customerId")int customerId);
	/**新增客户经理信息*/
	public int insertAccountManager(AccountManager accountManager);
	/**新增关键人信息*/
	public int insertKeyPersonInfo(KeyPersonInfo kpersonInfo);
	/**根据客户名称和编号查找客户信息*/
	public CustomerInfo  findCustInfoByNameAndCode(QueryParameters params);

}
