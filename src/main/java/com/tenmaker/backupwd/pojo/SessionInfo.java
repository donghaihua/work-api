package com.tenmaker.backupwd.pojo;

public class SessionInfo {
    private String id;

    private String UserUuid;

    private String Username;

    private String UserNickname;

    private String UserPhone;

    private String UserMail;

    private String devId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserUuid() {
        return UserUuid;
    }

    public void setUserUuid(String userUuid) {
        UserUuid = userUuid;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserNickname() {
        return UserNickname;
    }

    public void setUserNickname(String userNickname) {
        UserNickname = userNickname;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

}
