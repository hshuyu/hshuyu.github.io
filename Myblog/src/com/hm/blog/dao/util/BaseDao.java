package com.hm.blog.dao.util;

import java.sql.*;

public class BaseDao {

    //mybatis
    private static XmlParse xmlParse;

    static {
        xmlParse=new XmlParse("../config/dbconfig.xml");
        try {
            Class clz = Class.forName(xmlParse.getVal("driver-class"));
            //创建一个驱动对象
            //   Driver driver= (Driver) clz.newInstance();
            //注册驱动
            //  DriverManager.registerDriver(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接
     */
    public Connection getConn() {
        try {
            String url=xmlParse.getVal("url");
            String userName=xmlParse.getVal("username");
            String password=xmlParse.getVal("password");
            Connection connection = DriverManager.getConnection(url,userName , password);
        return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭方法
     */
    public void close(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
