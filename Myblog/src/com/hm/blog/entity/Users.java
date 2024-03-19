package com.hm.blog.entity;

/***
 * create table users_(
 * users_code int not null,--关注编号
 * users_user_code int not null,--用户编号（外键）
 * users_user_code1 int not null--关注用户编号（外键）
 */
public class Users {
    private Integer usersCode;
    private Integer usersUserCode;
    private Integer usersUserCode1;

    public Users(){
    }

    public Users(Integer usersCode, Integer usersUserCode, Integer usersUserCode1) {
        this.usersCode = usersCode;
        this.usersUserCode = usersUserCode;
        this.usersUserCode1 = usersUserCode1;
    }

    public Integer getUsersCode() {
        return usersCode;
    }

    public void setUsersCode(Integer usersCode) {
        this.usersCode = usersCode;
    }

    public Integer getUsersUserCode() {
        return usersUserCode;
    }

    public void setUsersUserCode(Integer usersUserCode) {
        this.usersUserCode = usersUserCode;
    }

    public Integer getUsersUserCode1() {
        return usersUserCode1;
    }

    public void setUsersUserCode1(Integer usersUserCode1) {
        this.usersUserCode1 = usersUserCode1;
    }
}
