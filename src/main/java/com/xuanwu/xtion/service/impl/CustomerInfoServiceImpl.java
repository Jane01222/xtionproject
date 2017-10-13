package com.xuanwu.xtion.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xuanwu.xtion.api.rest.SynchroCustomerInfoFacadeService;
import com.xuanwu.xtion.dao.CustomerInfoDao;
import com.xuanwu.xtion.domain.AccountManager;
import com.xuanwu.xtion.domain.CustomerInfo;
import com.xuanwu.xtion.domain.KeyPersonInfo;
import com.xuanwu.xtion.domain.QueryParameters;
import com.xuanwu.xtion.service.CustomerInfoService;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService{
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	@Autowired
	private CustomerInfoDao cusInfoDao;
	
	/**
	 * 新增客户信息
	 */
	@Override
	@Transactional
	public int insertCusInfo(CustomerInfo customerInfo) throws Exception{
		//首先判断客户信息是否存在，不存在则添加客户信息表数据，再依次向客户经理表和关键人信息表添加数据
	    int ret = 0;
	    cusInfoDao.insertCusInfo(customerInfo);
	    int customerId=customerInfo.getId();
		AccountManager[] managers = customerInfo.custList;
		for(AccountManager o:managers){
			o.setCustomerId(customerId);
			ret = cusInfoDao.insertAccountManager(o);
		}
		KeyPersonInfo[] keyInfos = customerInfo.keyList;
		for(KeyPersonInfo k:keyInfos){
			k.setCustomerId(customerId);
			ret =cusInfoDao.insertKeyPersonInfo(k);
		}
		return ret;
	}
	/**
	 * 更新客户信息
	 */
	@Override
	public int updateCustomerInfo(CustomerInfo customerInfo){
		return cusInfoDao.updateCustomerInfo(customerInfo);
	}
	/**
	 * 查询客户信息
	 */
	@Override
	public CustomerInfo findCustomerInfoByID(int id) {
		return cusInfoDao.findCustomerInfoByID(id);
	}
	/**
	 * 查询所有客户信息
	 */
	@Override
	public List<CustomerInfo> findCustomerInfos() {
		return cusInfoDao.findCustomerInfos();
	}
	/**
	 * 删除客户信息
	 */
	@Override
	public int deleteCustInfoById(int id) {
		return cusInfoDao.deleteCustInfoById(id);
		
	}
	/**
	 * 查询客户经理信息
	 */
	@Override
	public List<AccountManager> findAccountManagerByCustId(int customerId) {
		return cusInfoDao.findAccountManagerByCustId(customerId);
	}
	/**
	 * 查询关键人信息
	 */
	@Override
	public List<KeyPersonInfo> findKeyPerinfoByCustId(int customerId) {
		return cusInfoDao.findKeyPerinfoByCustId(customerId);
	}
	/***
	 * 插入客户经理信息
	 */
	@Override
	public int insertAccountManager(AccountManager accountManager) {
		return cusInfoDao.insertAccountManager(accountManager);
	}
	/**
	 * 插入客户关键人信息
	 */
	@Override
	public int insertKeyPersonInfo(KeyPersonInfo kpersonInfo) {
		return cusInfoDao.insertKeyPersonInfo(kpersonInfo);
	}
	@Override
	public CustomerInfo findCustInfoByNameAndCode(QueryParameters params) {
		return cusInfoDao.findCustInfoByNameAndCode(params);
	}

}
