package com.hm.blog.entity;

/***
 * 管理员表数据
 *   caretaker1_code int not null,--管理员编号
 *   caretaker1_name varchar (10) not null,--管理员账户
 *     caretaker1_ID_card varchar(20) not null,--管理员身份证
 *   caretaker1_pwd varchar(12) not null,--管理员密码
 *   caretaker1_tel varchar(20) not null,--管理员手机号
 *   caretaker1_avatar varchar(100),--管理员头像
 *   caretaker1_loginStatus int not null--管理员登录状态
 */
public class Caretaker {
    private Integer caretakerCode;
    private String caretakerName;
    private String caretakerId;
    private String caretakerPwd;
    private String caretakerTel;
    private String caretakerAvatar;
    private Integer caretakerLoginStatus;

    public Caretaker(){
    }

    public Caretaker(Integer caretakerCode, String caretakerName,String caretakerId, String caretakerPwd, String caretakerTel, String caretakerAvatar, Integer caretakerLoginStatus) {
        this.caretakerCode = caretakerCode;
        this.caretakerName = caretakerName;
        this.caretakerId=caretakerId;
        this.caretakerPwd = caretakerPwd;
        this.caretakerTel = caretakerTel;
        this.caretakerAvatar = caretakerAvatar;
        this.caretakerLoginStatus = caretakerLoginStatus;
    }

    public String getCaretakerId() {
        return caretakerId;
    }

    public void setCaretakerId(String caretakerId) {
        this.caretakerId = caretakerId;
    }

    public Integer getCaretakerCode() {
        return caretakerCode;
    }

    public void setCaretakerCode(Integer caretakerCode) {
        this.caretakerCode = caretakerCode;
    }

    public String getCaretakerName() {
        return caretakerName;
    }

    public void setCaretakerName(String caretakerName) {
        this.caretakerName = caretakerName;
    }

    public String getCaretakerPwd() {
        return caretakerPwd;
    }

    public void setCaretakerPwd(String caretakerPwd) {
        this.caretakerPwd = caretakerPwd;
    }

    public String getCaretakerTel() {
        return caretakerTel;
    }

    public void setCaretakerTel(String caretakerTel) {
        this.caretakerTel = caretakerTel;
    }

    public String getCaretakerAvatar() {
        return caretakerAvatar;
    }

    public void setCaretakerAvatar(String caretakerAvatar) {
        this.caretakerAvatar = caretakerAvatar;
    }

    public Integer getCaretakerLoginStatus() {
        return caretakerLoginStatus;
    }

    public void setCaretakerLoginStatus(Integer caretakerLoginStatus) {
        this.caretakerLoginStatus = caretakerLoginStatus;
    }
}
