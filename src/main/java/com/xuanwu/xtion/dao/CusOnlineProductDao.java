package com.xuanwu.xtion.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuanwu.xtion.domain.CusOnlineProduct;


public interface CusOnlineProductDao {
	/**新增客户上线产品信息*/
	public int addCusOnlineProduct(CusOnlineProduct onlineProduct);
	/**查询上线产品信息列表*/
	public List<CusOnlineProduct> findOnlineProducts();
	/**根据id查找上线产品信息*/
	public CusOnlineProduct findOnlineProductById(@Param("id") int id);
	/**判断产品是否已存在*/
	public Integer selectProductByNameAndCode(@Param("proCode")String proCode,@Param("proName")String proName);
}
