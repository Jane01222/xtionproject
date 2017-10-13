package com.xuanwu.xtion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xuanwu.xtion.domain.CusOnlineProduct;

@Service
public interface CusOnlineProductService {
	/**新增客户上线产品信息*/
	public int addCusOnlineProduct(CusOnlineProduct onlineProduct);
	/**查询上线产品信息列表*/
	public List<CusOnlineProduct> findOnlineProducts();
	/**根据id查找上线产品信息*/
	public CusOnlineProduct findOnlineProductById(int id);
	/**判断产品是否已存在*/
	public Integer selectProductByNameAndCode(String proCode,String proName);
}
