package com.hm.blog.dao.impl;


import com.hm.blog.dao.IUserAdminDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

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
 * 用户表增删改查
 */
public class UserAdminAdminDao implements IUserAdminDao {
    /**
     * 按条件查询
     * @param user
     * @return
     */
    @Override
    public User queryByInfo(User user) {
       User user_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select user1_code, user1_name, user1_tel, user_birth, user1_id, user1_pwd, user1_email, user1_register_time, user1_avatar, user1_loginstatus, user1_integral from user_ where user1_code=?";
        ResultSet resultSet = sqlCommand.query(sql, user.getUserCode());
        try {
            if (resultSet.next()) {
                user_ = new User();
                user_.setUserCode(resultSet.getInt("user1_code"));
                user_.setUserName(resultSet.getString("user1_name"));
                user_.setUserTel(resultSet.getString("user1_tel"));
                user_.setUserBirth(resultSet.getString("user_birth"));
                user_.setUserId(resultSet.getString("user1_id"));
                user_.setUserPwd(resultSet.getString("user1_pwd"));
                user_.setUserEmail(resultSet.getString("user1_email"));
                user_.setUserRegisterTime(resultSet.getString("user1_register_time"));
                user_.setUserAvatar(resultSet.getString("user1_avatar"));
                user_.setUserLoginStatus(resultSet.getInt("user1_loginStatus"));
                user_.setUserIntegral(resultSet.getInt("user1_integral"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return user_;
    }

    /**
     * 查询所有
     * @param user
     * @return
     */
    @Override
    public List<User> queryAll(User user) {
        List<User> users=new Vector<>();
        String sql = "select user1_code, user1_name, user1_tel, user_birth, user1_id, user1_pwd, user1_email, user1_register_time, user1_avatar, user1_loginstatus, user1_integral from user_";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            User user_=null;
            if (resultSet.next()) {
                user_ = new User();
                user_.setUserName(resultSet.getString("user1_name"));
                user_.setUserTel(resultSet.getString("user1_tel"));
                user_.setUserBirth(resultSet.getString("user_birth"));
                user_.setUserId(resultSet.getString("user1_id"));
                user_.setUserPwd(resultSet.getString("user1_pwd"));
                user_.setUserEmail(resultSet.getString("user1_email"));
                user_.setUserRegisterTime(resultSet.getString("user1_register_time"));
                user_.setUserAvatar(resultSet.getString("user1_avatar"));
                user_.setUserLoginStatus(resultSet.getInt("user1_loginStatus"));
                user_.setUserIntegral(resultSet.getInt("user1_integral"));
                users.add(user_);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    /**
     * 添加
     * @param user
     * @return
     */
    @Override
    public int insert(User user) {
        String sql="insert into user_(user1_code, user1_name, user1_tel, user_birth, user1_id, user1_pwd, user1_email, user1_register_time, user1_avatar, user1_loginstatus, user1_integral)\n" +
                "values (BU.nextval, ?,?,?, ?, ?,?, ?, ?, ?, ?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,new Object[]{user.getUserName(),user.getUserTel(),user.getUserBirth(),user.getUserId(),user.getUserPwd()
                ,user.getUserEmail(),user.getUserRegisterTime(),user.getUserAvatar(),0,0});
        return res;
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @Override
    public int update(User user) {
        String sql="update user_\n" +
                "   set user1_name = ?,\n" +
                "       user1_tel = ?,\n" +
                "       user_birth = ?,\n" +
                "       user1_id = ?,\n" +
                "       user1_pwd = ?,\n" +
                "       user1_email = ?,\n" +
                "       user1_register_time = ?,\n" +
                "       user1_avatar = ?,\n" +
                "       user1_loginstatus = ?,\n" +
                "       user1_integral = ?\n" +
                " where user1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,new Object[]{user.getUserName(),user.getUserTel(),user.getUserBirth(),user.getUserId(),user.getUserPwd()
                ,user.getUserEmail(),user.getUserRegisterTime(),user.getUserAvatar(),user.getUserLoginStatus(),user.getUserIntegral(),user.getUserIntegral()});
        return res;
    }

    /**
     * 删除
     * @param user
     * @return
     */
    @Override
    public int delete(User user) {
        String sql="delete user_ where user1_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,user.getUserCode());
    }


    @Override
    public int queryCount(User user) {
        String sql = "select count(user1_code) u from user_ ";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            if (resultSet.next()){
                return resultSet.getInt("u");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<User> queryAll(User user, int page, int pageSize) {
        int start=(page-1)*pageSize;
        int end=page*pageSize;
        List<User> users = new Vector<>();
        String sql = "select * from\n" +
                "  (select rownum r, user1_code, user1_name, user1_tel, user_birth, user1_id, user1_pwd, user1_email, user1_register_time, user1_avatar, user1_loginstatus, user1_integral\n" +
                "  from user_ where rownum<=?) where r>?";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql,end,start);
        try {
            User user_ = null;
            while (resultSet.next()){
                user_ = new User();
                user_.setUserCode(resultSet.getInt("user1_code"));
                user_.setUserName(resultSet.getString("user1_name"));
                user_.setUserTel(resultSet.getString("user1_tel"));
                user_.setUserBirth(resultSet.getString("user_birth"));
                user_.setUserId(resultSet.getString("user1_id"));
                user_.setUserPwd(resultSet.getString("user1_pwd"));
                user_.setUserEmail(resultSet.getString("user1_email"));
                user_.setUserRegisterTime(resultSet.getString("user1_register_time"));
                user_.setUserAvatar(resultSet.getString("user1_avatar"));
                user_.setUserLoginStatus(resultSet.getInt("user1_loginstatus"));
                user_.setUserIntegral(resultSet.getInt("user1_integral"));
                users.add(user_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int edit(User user) {
        String sql="update user_\n" +
                "   set user1_loginstatus = ?\n" +
                " where user1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql, user.getUserLoginStatus(),user.getUserCode());
        return res;
    }

    @Override
    public List<User> queryAlls(User user, int page, int pageSize) {
        int start=(page-1)*pageSize;
        int end=page*pageSize;
        List<User> users = new Vector<>();
        String sql = "select * from\n" +
                "  (select rownum r, user1_code, user1_name, user1_tel, user_birth, user1_id, user1_pwd, user1_email, user1_register_time, user1_avatar, user1_loginstatus, user1_integral\n" +
                "  from user_ where user1_name=? and rownum<=?) where r>?";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql,user.getUserName(),end,start);
        try {
            User user_ = null;
            while (resultSet.next()){
                user_ = new User();
                user_.setUserCode(resultSet.getInt("user1_code"));
                user_.setUserName(resultSet.getString("user1_name"));
                user_.setUserTel(resultSet.getString("user1_tel"));
                user_.setUserBirth(resultSet.getString("user_birth"));
                user_.setUserId(resultSet.getString("user1_id"));
                user_.setUserPwd(resultSet.getString("user1_pwd"));
                user_.setUserEmail(resultSet.getString("user1_email"));
                user_.setUserRegisterTime(resultSet.getString("user1_register_time"));
                user_.setUserAvatar(resultSet.getString("user1_avatar"));
                user_.setUserLoginStatus(resultSet.getInt("user1_loginstatus"));
                user_.setUserIntegral(resultSet.getInt("user1_integral"));
                users.add(user_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
