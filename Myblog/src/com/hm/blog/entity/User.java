package com.hm.blog.entity;
/*
--用户表
create table user_(
  user1_code int not null,--用户编号
  user1_name varchar(10) not null,--用户名
  user1_tel varchar(20) not null,--用户手机号
  user1_id varchar(20)  not null,--用户身份证
  user1_pwd varchar(12) not null,--用户密码
  user1_email varchar(20),--用户邮箱
  user1_register_time varchar(30),--注册时间
  user1_avatar varchar(100),--头像
  user1_loginStatus int--状态
 */

/**
 * 用户表
 */
public class User {
    private Integer userCode;//用户编号
    private  String userName;//用户名
    private String userTel;//用户手机号
    private String userBirth;//用户出生年月
    private String userId;//用户身份证
    private String userPwd;//用户密码
    private String userEmail;//用户邮箱
    private String userRegisterTime;//注册时间
    private String userAvatar;//头像
    private Integer userLoginStatus;//状态
    private Integer userIntegral; //

    public User() {

    }

    public User(Integer userCode, String userName, String userTel, String userBirth, String userId, String userPwd, String userEmail, String userRegisterTime, String userAvatar, Integer userLoginStatus, Integer userIntegral) {
        this.userCode = userCode;
        this.userName = userName;
        this.userTel = userTel;
        this.userBirth = userBirth;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.userRegisterTime = userRegisterTime;
        this.userAvatar = userAvatar;
        this.userLoginStatus = userLoginStatus;
        this.userIntegral = userIntegral;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(String userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getUserLoginStatus() {
        return userLoginStatus;
    }

    public void setUserLoginStatus(Integer userLoginStatus) {
        this.userLoginStatus = userLoginStatus;
    }

    public Integer getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(Integer userIntegral) {
        this.userIntegral = userIntegral;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode=" + userCode +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userId='" + userId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userRegisterTime='" + userRegisterTime + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userLoginStatus=" + userLoginStatus +
                '}';
    }
}
