package com.xuanwu.xtion.domain;
/***
 * @Discription 收费项目信息实体类
 * @author <a href="liuchunyan@wxchina.com">Chunyan.Liu</a>
 * @Date 2017-07-31
 */
public class CusFeeItemsList {
	/**主键*/
	private Integer id;
	/**订单ID*/
	private Integer cusOrderId;
	/**项目编号*/
	private String proCode;
	/**项目名称*/
	private String proName;
	/**项目单位*/
	private String proUnit;
	/**项目数量*/
	private String proNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCusOrderId() {
		return cusOrderId;
	}
	public void setCusOrderId(Integer cusOrderId) {
		this.cusOrderId = cusOrderId;
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
	public String getProUnit() {
		return proUnit;
	}
	public void setProUnit(String proUnit) {
		this.proUnit = proUnit;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	

}
