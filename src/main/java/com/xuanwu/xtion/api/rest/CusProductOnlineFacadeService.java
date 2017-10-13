package com.xuanwu.xtion.api.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xuanwu.xtion.domain.CusOnlineProduct;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.service.CusOnlineProductService;
/**
 * @Discription 客户产品上线通知接口
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-25
 *
 */
@RestController
public class CusProductOnlineFacadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(CusProductOnlineFacadeService.class);
	
	@Autowired
	private CusOnlineProductService onlineProductService;
	/**
	 * 同步客户信息
	 * @param request
	 * @param customerInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="api/xtion/product",method=RequestMethod.POST)
	public JsonResp addOnlineProductInfo(HttpServletRequest request,@RequestBody CusOnlineProduct onlineProduct){
		JsonResp jsonResp =  JsonResp.failure("产品上线通知失败");
		try {
			//首先判断客户产品信息是否存在
			Integer isExists = 
					onlineProductService.selectProductByNameAndCode(onlineProduct.getProCode(), onlineProduct.getProName());
			if(isExists>0){
				return JsonResp.failure("处理失败：上线产品信息已存在！");
			}
			Date publishTime = new Date(onlineProduct.getPubTime());
			Date curDate = new Date();
			onlineProduct.setPublishTime(publishTime);
			onlineProduct.setCreateTime(curDate);
			onlineProduct.setUpdateTime(curDate);
			boolean ret = onlineProductService.addCusOnlineProduct(onlineProduct)>0;
			if(ret){jsonResp=JsonResp.success("产品上线通知成功");}
		} catch (Exception e) {
			logger.error("add onlineProduct error caused by:",e);
		}
		return jsonResp;
	}
	

	
	/**
	 * 根据id查询客户上线产品
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="api/xtion/product/{id}",method=RequestMethod.GET)
	public JsonResp selectOnlineProductById(HttpServletRequest request,@PathVariable("id")Integer id){
		JsonResp jsonResp =  JsonResp.failure("查询上线产品信息失败");
		try {
			CusOnlineProduct onlineProduct = onlineProductService.findOnlineProductById(id);
			Map<String,Object> respMap = new HashMap<String,Object>();
			respMap.put("onlineProduct", onlineProduct);
			jsonResp=JsonResp.success(respMap);
		} catch (Exception e) {
			logger.error("select onlineProduct info error caused by:",e);
		}
		return jsonResp;
	}
	
	/**
	 * 查询客户信息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="api/xtion/products",method=RequestMethod.GET)
	public JsonResp selectCusInfos(HttpServletRequest request){
		JsonResp jsonResp =  JsonResp.failure("查询上线产品信息列表失败");
		try {
			List<CusOnlineProduct> onlineProducts = onlineProductService.findOnlineProducts();
			Map<String,Object> respMap = new HashMap<String,Object>();
			respMap.put("onlineProducts", onlineProducts);
			jsonResp=JsonResp.success(respMap);
		} catch (Exception e) {
			logger.error("select onlineProduct list error caused by:",e);
		}
		return jsonResp;
	}
	

}
