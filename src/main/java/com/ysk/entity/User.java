package com.ysk.entity;


import java.sql.Timestamp;

/**
 * Created by Y.S.K on 2017/8/1 in wx_bot.
 */
public class User {
    private Integer id;
    private String userName;
    private  String password;
    private  String email;
    private Timestamp expirationTime;
    private  SendRecord sendRecord;
    private CDK cdk;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public SendRecord getSendRecord() {
        return sendRecord;
    }

    public void setSendRecord(SendRecord sendRecord) {
        this.sendRecord = sendRecord;
    }

    public CDK getCdk() {
        return cdk;
    }

    public void setCdk(CDK cdk) {
        this.cdk = cdk;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", expirationTime=" + expirationTime +
                ", sendRecord=" + sendRecord +
                ", cdk=" + cdk +
                '}';
    }
}
