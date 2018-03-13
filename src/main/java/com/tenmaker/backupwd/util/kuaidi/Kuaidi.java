package com.tenmaker.backupwd.util.kuaidi;

import java.util.ArrayList;
import java.util.List;

public class Kuaidi {
	private String codenumber;
	private String nu; // 单号
	private String updatetime; // 更新时间
	/**
	 * 查询结果状态： 0：物流单暂无结果， 1：查询成功， 2：接口出现异常，
	 */
	private int status;
	/**
	 * 快递单当前的状态 ： 0：在途，即货物处于运输过程中； 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息； 2：疑难，货物寄送过程出了问题；
	 * 3：签收，收件人已签收； 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收； 5：派件，即快递正在进行同城派件；
	 * 6：退回，货物正处于退回发件人的途中；
	 */
	private int state;
	private String departure;// 起点
	private String destination;// 终点
	private List<KuaidiData> data = new ArrayList<KuaidiData>();// 具体数据

	// 无用字段，必须有，不然转不了java对象
	private String message;
	private String ischeck;
	private String com;
	private String condition;
	private String companytype;
	private String signname;
	private String signedtime;
	private String pattern;
	private String addressee;
	private String pickuptime;

	public String getPickuptime() {
		return pickuptime;
	}

	public void setPickuptime(String pickuptime) {
		this.pickuptime = pickuptime;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getCodenumber() {
		return codenumber;
	}

	public void setCodenumber(String codenumber) {
		this.codenumber = codenumber;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<KuaidiData> getData() {
		return data;
	}

	public void setData(List<KuaidiData> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getSignedtime() {
		return signedtime;
	}

	public void setSignedtime(String signedtime) {
		this.signedtime = signedtime;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
