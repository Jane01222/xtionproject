package com.xuanwu.xtion.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * @Discription 客户信息实体
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-31
 */
public class CustomerInfo extends BaseEntity{
	private Integer id;
	/**客户名称*/
	private String ecName;
	/**客户编码*/
	private String ecCode;
	/**客户级别*/
	private String ecLevel;
	/**产品编号*/
	private String proCode;	
	/**地址*/
	private String ecAddress;
	/**邮编*/
	private String ecPostCode;
	/** 客户状态 */
	private Integer ecStatus;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**客服记录是否已开通企业状态(1:未开通；2表已开通)*/
	private Integer isOpenStatus;
	/**客户经理信息*/
	public AccountManager[] custList; 
	/**关键人信息*/
	public KeyPersonInfo[] keyList;
	/**扩展参数字段*/
	private HashMap<String,String> paramMap;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public String getEcCode() {
		return ecCode;
	}
	public void setEcCode(String ecCode) {
		this.ecCode = ecCode;
	}
	public String getEcLevel() {
		return ecLevel;
	}
	public void setEcLevel(String ecLevel) {
		this.ecLevel = ecLevel;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getEcAddress() {
		return ecAddress;
	}
	public void setEcAddress(String ecAddress) {
		this.ecAddress = ecAddress;
	}
	public String getEcPostCode() {
		return ecPostCode;
	}
	public void setEcPostCode(String ecPostCode) {
		this.ecPostCode = ecPostCode;
	}
	public Integer getEcStatus() {
		return ecStatus;
	}
	public void setEcStatus(Integer ecStatus) {
		this.ecStatus = ecStatus;
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
	public Integer getIsOpenStatus() {
		return isOpenStatus;
	}
	public void setIsOpenStatus(Integer isOpenStatus) {
		this.isOpenStatus = isOpenStatus;
	}
	public AccountManager[] getCustList() {
		return custList;
	}
	public void setCustList(AccountManager[] custList) {
		this.custList = custList;
	}
	public KeyPersonInfo[] getKeyList() {
		return keyList;
	}
	public void setKeyList(KeyPersonInfo[] keyList) {
		this.keyList = keyList;
	}
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(HashMap<String, String> paramMap) {
		this.paramMap = paramMap;
	}
}
