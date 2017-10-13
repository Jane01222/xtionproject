package com.xuanwu.xtion.domain;
/**
 * @Discription 关键人信息说明
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-27
 */
public class KeyPersonInfo {
	/**主键*/
	private Integer id;
	/**客户信息表主键*/
	private Integer customerId;
	/**关键人角色*/
	private String keyRole;
	/**关键人姓名*/
	private String keyName;
	/**关键人联系电话*/
	private String keyPhone;
	/**关键人邮箱*/
	private String keyEmail;
	
	
	public KeyPersonInfo() {
		super();
	}
	public KeyPersonInfo(Integer id, Integer customerId, String keyRole,
			String keyName, String keyPhone, String keyEmail) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.keyRole = keyRole;
		this.keyName = keyName;
		this.keyPhone = keyPhone;
		this.keyEmail = keyEmail;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getKeyRole() {
		return keyRole;
	}
	public void setKeyRole(String keyRole) {
		this.keyRole = keyRole;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getKeyPhone() {
		return keyPhone;
	}
	public void setKeyPhone(String keyPhone) {
		this.keyPhone = keyPhone;
	}
	public String getKeyEmail() {
		return keyEmail;
	}
	public void setKeyEmail(String keyEmail) {
		this.keyEmail = keyEmail;
	}
	
}
