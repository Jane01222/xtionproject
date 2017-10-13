package com.xuanwu.xtion.utils;

import java.util.Map;

import com.xuanwu.xtion.api.rest.ProductOrderConfirmFacadeService;

public class OrderConfirmTask implements Runnable {
	
	private Map<String,Object> data;
	private Integer id;
	
	private ProductOrderConfirmFacadeService productOrderConfirmController;
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);//等待1000ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		productOrderConfirmController.productOrderConfirm(id);	
	}
	
	public OrderConfirmTask(Integer id, ProductOrderConfirmFacadeService productOrderConfirmFacadeService) {
		this.id = id;
		this.productOrderConfirmController = productOrderConfirmFacadeService;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}