package com.tenmaker.backupwd.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String uuid;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String mail;

    private Integer sex;

    private String avatar;

    private String title;

    private String department;

    private Integer state;

    private Integer role;

    private Date createdat;

    private Date updatedat;

    private Integer subcompanyid;

    private String accesspassword;

    private String wechatid;

    private Integer ispasswordchanged;

    private String sid;

    private String mailloginid;

    private String noaccess;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Date updatedat) {
        this.updatedat = updatedat;
    }

    public Integer getSubcompanyid() {
        return subcompanyid;
    }

    public void setSubcompanyid(Integer subcompanyid) {
        this.subcompanyid = subcompanyid;
    }

    public String getAccesspassword() {
        return accesspassword;
    }

    public void setAccesspassword(String accesspassword) {
        this.accesspassword = accesspassword == null ? null : accesspassword.trim();
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid == null ? null : wechatid.trim();
    }

    public Integer getIspasswordchanged() {
        return ispasswordchanged;
    }

    public void setIspasswordchanged(Integer ispasswordchanged) {
        this.ispasswordchanged = ispasswordchanged;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getMailloginid() {
        return mailloginid;
    }

    public void setMailloginid(String mailloginid) {
        this.mailloginid = mailloginid == null ? null : mailloginid.trim();
    }

    public String getNoaccess() {
        return noaccess;
    }

    public void setNoaccess(String noaccess) {
        this.noaccess = noaccess == null ? null : noaccess.trim();
    }
}