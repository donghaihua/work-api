package com.tenmaker.backupwd.model;

// Generated 2014-9-9 12:45:23 by Hibernate Tools 3.4.0.CR1

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户账户类
 */
public class PassportAccount implements java.io.Serializable {
	private Long id;
	/**
	 * 2：普通客户  3：分销商
	 */
	private Integer userType;
	/**
	 * 用户名称默认为手机号，可以更改一次
	 */
	private String userName;
	private String password;
	private String telephone;
	private String email;
	private String userQQ;
	/**
	 * 判断用户名是否已经修改过
	 */
	private Integer unameState;

	private Integer telState;
	/**
	 * 判断用户名是否已经验证过邮箱了
	 */
	private Integer emailState;
	/**
	 * 0 正常  ，1冻结
	 */
	private Integer state;
	private Date createTime;	//added on 2018-2-26

	private String servierPwd;
	private Set thirdAccount = new HashSet(0);
	private Set passportAddresses = new HashSet(0);
	private Set passportInfos = new HashSet(0);
	private Set passportTickets = new HashSet(0);
	private Set passportLevelsForAccountId = new HashSet(0);
	private Set passportLevelsForReferId = new HashSet(0);
	
	private Set works = new HashSet(0);
	
	public PassportAccount() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getServierPwd() {
		return servierPwd;
	}

	public void setServierPwd(String servierPwd) {
		this.servierPwd = servierPwd;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	public Integer getUnameState() {
		return unameState;
	}

	public void setUnameState(Integer unameState) {
		this.unameState = unameState;
	}

	public Integer getTelState() {
		return telState;
	}

	public void setTelState(Integer telState) {
		this.telState = telState;
	}

	public Integer getEmailState() {
		return this.emailState;
	}

	public void setEmailState(Integer emailState) {
		this.emailState = emailState;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set getPassportAddresses() {
		return passportAddresses;
	}

	public void setPassportAddresses(Set passportAddresses) {
		this.passportAddresses = passportAddresses;
	}

	public Set getPassportInfos() {
		return passportInfos;
	}

	public void setPassportInfos(Set passportInfos) {
		this.passportInfos = passportInfos;
	}

	public Set getPassportTickets() {
		return passportTickets;
	}

	public void setPassportTickets(Set passportTickets) {
		this.passportTickets = passportTickets;
	}

	public Set getThirdAccount() {
		return thirdAccount;
	}

	public void setThirdAccount(Set thirdAccount) {
		this.thirdAccount = thirdAccount;
	}

	public Set getPassportLevelsForAccountId() {
		return passportLevelsForAccountId;
	}

	public void setPassportLevelsForAccountId(Set passportLevelsForAccountId) {
		this.passportLevelsForAccountId = passportLevelsForAccountId;
	}

	public Set getPassportLevelsForReferId() {
		return passportLevelsForReferId;
	}

	public void setPassportLevelsForReferId(Set passportLevelsForReferId) {
		this.passportLevelsForReferId = passportLevelsForReferId;
	}


	public Set getWorks() {
		return works;
	}

	public void setWorks(Set works) {
		this.works = works;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
