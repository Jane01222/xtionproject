package com.xuanwu.xtion.domain;

/**
 * @Discription 客户经理实体类
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-26
 *
 */
public class AccountManager{
	/**主键*/
	private Integer id;
	/**客户信息表主键*/
	private Integer customerId;
	/**客户经理姓名*/
	private String staffName;
	/**客户经理联系电话*/
	private String staffPhone;
	/**客户经理手机号*/
	private String staffMobile;
	
	public AccountManager() {
		super();
	}
	public AccountManager(Integer id, Integer customerId, String staffName,
			String staffPhone, String staffMobile) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.staffName = staffName;
		this.staffPhone = staffPhone;
		this.staffMobile = staffMobile;
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getStaffMobile() {
		return staffMobile;
	}
	public void setStaffMobile(String staffMobile) {
		this.staffMobile = staffMobile;
	}
	

}
