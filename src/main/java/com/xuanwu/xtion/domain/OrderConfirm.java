package com.xuanwu.xtion.domain;
/**
 * @Discription 订单确认消息实体
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-08-01
 */
public class OrderConfirm {
	/**订单编号*/
	private String orderId;
	/**产品订购确认结果（success：成功，fail：失败）*/
	private ConResult conResult;
	/**确认说明备注*/
	private String conNote;
	/**订单费用*/
	private String orderPrice;
	/**运营平台分配的apiKey*/
	private String apiKey;
	/**Unix时间戳*/
	private Long timestamp;
	/**随机数，生成规则：MD5(uuid+timestamp)*/
	private String rand;
	/**签名*/
	private String sign;
	
	public enum ConResult {
		/**
		 * 成功
		 */
		SUCCESS(0, "success"),
		/**
		 * 失败
		 */
		FAIL(1, "fail");
		
		private int index;
		
		private String code;
		
		private ConResult(int index, String code) {
			this.index = index;
			this.code = code;
		}
		public int getIndex() {
			return index;
		}
		public static ConResult getConResult(int index) {
			switch (index) {
			case 0:
				return SUCCESS;
			case 1:
				return FAIL;
			default:
				return null;
			}
		}
		public String getCode() {
			return code;
		}
	}
	public String getConResultName() {
		switch (conResult) {
		case SUCCESS:
			return "成功";
		case FAIL:
			return "失败";
		default:
			return "";
		}
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public ConResult getConResult() {
		return conResult;
	}
	public void setConResult(ConResult conResult) {
		this.conResult = conResult;
	}
	public String getConNote() {
		return conNote;
	}
	public void setConNote(String conNote) {
		this.conNote = conNote;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}

}
