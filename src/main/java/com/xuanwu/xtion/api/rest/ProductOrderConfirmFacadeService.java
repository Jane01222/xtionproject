package com.xuanwu.xtion.api.rest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.xuanwu.xtion.domain.CustomerOrder;
import com.xuanwu.xtion.domain.JsonResp;
import com.xuanwu.xtion.domain.OrderConfirm.ConResult;
import com.xuanwu.xtion.service.CustomerOrderService;
import com.xuanwu.xtion.utils.JsonUtil;
import com.xuanwu.xtion.utils.PropertiesUtil;

/**
 * @Discription 产品订购确认接口
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-31
 */
@RestController
public class ProductOrderConfirmFacadeService {
	
	private static final Logger logger = LoggerFactory.getLogger(SynchroCustomerInfoFacadeService.class);
	
	@Autowired
	private CustomerOrderService orderService;
	
	@RequestMapping(value="api/xtion/orderConfirm/{id}",method=RequestMethod.GET)
	public JsonResp productOrderConfirm(@PathVariable("id") Integer id){
		logger.debug("api/xtion/orderConfirm/"+id+":"+JSONArray.toJSONString(id));
		JsonResp jsonResp = JsonResp.failure("订单确认请求失败");
		try {
			String serverUri = PropertiesUtil.getPropertyValue("param.properties", "serverUri");
			String apiKey = PropertiesUtil.getPropertyValue("param.properties", "apiKey");
			String secretKey = PropertiesUtil.getPropertyValue("param.properties", "secretKey");
			CustomerOrder cusOrder = orderService.findOrderInfoById(id);
			String randStr = new StringBuilder(UUID.randomUUID().toString()).append(String.valueOf(System.currentTimeMillis())).toString();
			randStr = MD5Encoder(randStr);
			String conNote = "confirm successed";
			String tempStamp =String.valueOf(System.currentTimeMillis());
			String orderId = cusOrder.getOrderId();
			String orderPrice = cusOrder.getOrderFee();
			StringBuilder sb = new StringBuilder();
			String stringSignTemp = sb.append("apiKey=").append(apiKey).append("&conNote=").append(conNote)
					.append("&conResult=").append(ConResult.SUCCESS.getCode()).append("&orderId=").append(orderId)
					.append("&orderPrice=").append(orderPrice).append("&rand=").append(randStr).append("&timestamp=")
					.append(tempStamp).append("&secretKey=").append(secretKey).toString();
			stringSignTemp = MD5Encoder(stringSignTemp);
			Form form = new Form();
			form.add("orderId", orderId);
			form.add("conResult", ConResult.SUCCESS.getCode());
			form.add("apiKey", apiKey);
			form.add("conNote", conNote);
			form.add("orderPrice", orderPrice);
			form.add("timestamp", tempStamp);
			form.add("rand", randStr);
			form.add("sign", stringSignTemp);
			Client client = Client.create();
	        WebResource webResource = client.resource(serverUri);
	        ClientResponse result = webResource.header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
	                                           .post(ClientResponse.class, form);
//			HttpClientUtil.doPost(serverUri, JsonUtil.getObject(JsonUtil.getJson(orderConfirm), HashMap.class), String.class);
	        jsonResp = JsonResp.success(result.getEntity(String.class));
		} catch (Exception e) {
			logger.error("productOrderConfirm error caused by", e);
		}
		return jsonResp;
	}
	
	

	/**
	 * 生成签名，签名生成规则如下：
	 * 第一步，设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。 
		特别注意以下重要规则： 
		1.参数名ASCII码从小到大排序（字典序）； 
		2.如果参数的值为空不参与签名； 
		3.参数名区分大小写； 
		第二步，在stringA最后拼接上secretKey得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。
	 * @param o
	 * @param secretKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	static String generateSign(Object o,String secretKey){
		try {
		HashMap<String,Object> map = JsonUtil.getObject(JsonUtil.getJson(o), HashMap.class);
		ArrayList<String> list = new ArrayList<String>();
		String signStr = "";
	    for(Entry<String, Object> entry:map.entrySet()){
	    	if(entry.getValue()!=null){
	    		list.add(entry.getKey());
	    	}
	    }
	    list.sort(new KeyComparator());
	    System.out.println(list);
	   for(int i=0;i<list.size();i++){
		   signStr += list.get(i)+"="+map.get(list.get(i))+"&"; 
	   }
		String stringSignTemp = signStr.substring(0,signStr.length()-1).concat(secretKey);
		System.out.println(stringSignTemp.getBytes().length);
		//对字符串进行MD5加密
		stringSignTemp = MD5Encoder(stringSignTemp);
		return stringSignTemp;
		} catch (Exception e) {
			logger.error("generate Sign error caused by", e);
			e.printStackTrace();
		}
		return null;
	}
	 
	/**
	 * 
	 * @author Administrator
	 *
	 */
	 static class KeyComparator implements Comparator<Object> {  
	        public int compare(Object o1, Object o2) {// 实现接口中的方法  
	        	String p1 = (String) o1; //强制转换  
	        	String p2 = (String) o2;  
	            return p1.compareTo(p2);  
	        }  
	    }
	 
	 /**
	  * 对字符串进行MD5加密
	  * @param str
	  * @return
	  */
	 static String MD5Encoder(String str){
		 MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(str.getBytes());
			StringBuilder builder = new StringBuilder();
			for (byte b : bs) {
				if ((b & 0xff) >> 4 == 0) {
					builder.append("0");
				} 
				builder.append(Integer.toHexString(b & 0xff));
			}
			str = builder.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			str="";
			logger.error("MD5Encoder error caused by", e);
		}
		 return str;
	 }
}
