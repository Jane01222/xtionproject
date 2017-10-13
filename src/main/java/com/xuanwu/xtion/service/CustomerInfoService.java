package com.xuanwu.xtion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuanwu.xtion.domain.AccountManager;
import com.xuanwu.xtion.domain.CustomerInfo;
import com.xuanwu.xtion.domain.KeyPersonInfo;
import com.xuanwu.xtion.domain.QueryParameters;

@Service
public interface CustomerInfoService {
	
	/**新增客户信息
	 * @throws Exception */
	public int insertCusInfo(CustomerInfo customerInfo) throws Exception;
	/**更新客户信息*/
	public int updateCustomerInfo(CustomerInfo customerInfo);
	/**查询客户信息*/
	public CustomerInfo findCustomerInfoByID(int id);
	/**查询所有客户信息*/
	public List<CustomerInfo> findCustomerInfos();
	/**删除客户信息*/
	public int deleteCustInfoById(int id);
	/**查询客户经理信息*/
	public List<AccountManager> findAccountManagerByCustId(int customerId);
	/**查询关键人信息*/
	public List<KeyPersonInfo> findKeyPerinfoByCustId(int customerId);
	/**新增客户经理信息*/
	int insertAccountManager(AccountManager accountManager);
	/**新增关键人信息*/
	public int insertKeyPersonInfo(KeyPersonInfo kpersonInfo);
	/**根据客户名称和编号查找客户信息*/
	public CustomerInfo  findCustInfoByNameAndCode(QueryParameters params);

}
