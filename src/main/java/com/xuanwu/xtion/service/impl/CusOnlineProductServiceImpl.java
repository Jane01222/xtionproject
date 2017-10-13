package com.xuanwu.xtion.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuanwu.xtion.dao.CusOnlineProductDao;
import com.xuanwu.xtion.domain.CusOnlineProduct;
import com.xuanwu.xtion.service.CusOnlineProductService;

@Service
public class CusOnlineProductServiceImpl implements CusOnlineProductService{
	
	private static final Logger logger = LoggerFactory.getLogger(CusOnlineProductServiceImpl.class);
	@Autowired
	private CusOnlineProductDao onlineProductDao;
	/**
	 *新增客户上线产品信息 
	 */
	@Override
	public int addCusOnlineProduct(CusOnlineProduct onlineProduct) {
		return onlineProductDao.addCusOnlineProduct(onlineProduct);
	}
	/**
	 * 查询客户上线产品列表
	 */
	@Override
	public List<CusOnlineProduct> findOnlineProducts() {
		return onlineProductDao.findOnlineProducts();
	}
	/**
	 * 根据ID查询出客户上线产品信息
	 */
	@Override
	public CusOnlineProduct findOnlineProductById(int id) {
		return onlineProductDao.findOnlineProductById(id);
	}
	@Override
	public Integer selectProductByNameAndCode(String proCode, String proName) {
		return onlineProductDao.selectProductByNameAndCode(proCode, proName);
	}
	
}
