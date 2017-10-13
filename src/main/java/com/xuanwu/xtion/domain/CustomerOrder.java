package com.xuanwu.xtion.domain;

import java.util.Date;
import java.util.HashMap;

/***
 * @Discription 客户订单实体类
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-31
 */
public class CustomerOrder extends BaseEntity{
	/**主键*/
	private Integer id;
	/**EC客户编码*/
	private String ecCode;
	/**订单号*/
	private String orderId;
	/**订购时间*/
	private Long orderTime;
	/**处理时间格式*/
	private Date orderdTime;
	/**产品编号*/
	private String proCode;
	/**订购数量*/
	private Integer orderNum;
	/**资费编号*/
	private String feeCode;
	/**资费名称*/
	private String feeName;
	/**资费单价*/
	private String unitPrice;
	/**订单费用*/
	private String orderFee;
	/**计费周期*/
	private String feePeriod;
	/**有效期，单位为月*/
	private String validity;
	/**订单备注*/
	private String orderNote;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**项目资费信息*/
	public CusFeeItemsList[] feeProList;
	/**扩展参数字段*/
	private HashMap<String,String> paramMap;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEcCode() {
		return ecCode;
	}
	public void setEcCode(String ecCode) {
		this.ecCode = ecCode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Long orderTime) {
		this.orderTime = orderTime;
	}
	public Date getOrderdTime() {
		return orderdTime;
	}
	public void setOrderdTime(Date orderdTime) {
		this.orderdTime = orderdTime;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getFeeName() {
		return feeName;
	}
	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getOrderFee() {
		return orderFee;
	}
	public void setOrderFee(String orderFee) {
		this.orderFee = orderFee;
	}
	public String getFeePeriod() {
		return feePeriod;
	}
	public void setFeePeriod(String feePeriod) {
		this.feePeriod = feePeriod;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public CusFeeItemsList[] getFeeProList() {
		return feeProList;
	}
	public void setFeeProList(CusFeeItemsList[] feeProList) {
		this.feeProList = feeProList;
	}
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(HashMap<String, String> paramMap) {
		this.paramMap = paramMap;
	}

}
