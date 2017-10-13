package com.xuanwu.xtion.domain;

import java.util.Date;
import java.util.HashMap;

/**
 * @Discription 客户上线产品信息实体
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-08-01
 */
public class CusOnlineProduct extends BaseEntity{
	private Integer id;
	/**平台产品编码*/
	private String proCode;
	/**平台产品名称*/
	private String proName;
	/**CRM策划名称*/
	private String offerName;
	/**产品状态：1：产品发布 2：产品暂停*/
	private Integer proStatus;
	/**产品发布时间*/
	private Long pubTime;
	/**用于转换的产品发布时间*/
	private Date publishTime;
	/**创建时间*/
	private Date createTime;
	/**更新时间*/
	private Date updateTime;
	/**参数Map,有定制化参数使用*/
	private HashMap<String,String> paramMap;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public Integer getProStatus() {
		return proStatus;
	}
	public void setProStatus(Integer proStatus) {
		this.proStatus = proStatus;
	}
	public Long getPubTime() {
		return pubTime;
	}
	public void setPubTime(Long pubTime) {
		this.pubTime = pubTime;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
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
	public HashMap<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(HashMap<String, String> paramMap) {
		this.paramMap = paramMap;
	}

}
