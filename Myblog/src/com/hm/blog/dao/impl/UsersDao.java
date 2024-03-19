package com.hm.blog.dao.impl;



import com.hm.blog.dao.IUsersDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class UsersDao implements IUsersDao {
    @Override
    public Users queryByInfo(Users users) {
        Users users_ = null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select users_code, users_user_code, users_user_code1 from users_ where users_code=?";
        ResultSet resultSet = sqlCommand.query(sql,users.getUsersCode());
        try {
            if (resultSet.next()){
                users_ = new Users();
                users_.setUsersCode(resultSet.getInt("users_code"));
                users_.setUsersUserCode(resultSet.getInt("users_user_code"));
                users_.setUsersUserCode1(resultSet.getInt("users_user_code1"));
            }
            return users_;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }
        return null;
    }

    @Override
    public List<Users> queryAll(Users users) {
        List<Users> userss = new Vector<>();
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select users_code, users_user_code, users_user_code1 from users_";
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            Users users_ = null;
            while (resultSet.next()){
                users_ = new Users();
                users_.setUsersCode(resultSet.getInt("users_code"));
                users_.setUsersUserCode(resultSet.getInt("users_user_code"));
                users_.setUsersUserCode1(resultSet.getInt("users_user_code1"));
                userss.add(users_);
            }
            return userss;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Users users) {
        String sql = "insert into users_(users_code, users_user_code, users_user_code1) values(BUS.nextval,?,?)";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,users.getUsersUserCode(),users.getUsersUserCode1());
        return res;
    }

    @Override
    public int update(Users users) {
        String sql = "update users_ set users_user_code=?, users_user_code1=? where users_code=?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,users.getUsersUserCode(),users.getUsersUserCode1(),users.getUsersCode());
        return res;
    }

    @Override
    public int delete(Users users) {
        String sql = "delete users_ where users_code=?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,users.getUsersCode());
        return res;
    }
}
