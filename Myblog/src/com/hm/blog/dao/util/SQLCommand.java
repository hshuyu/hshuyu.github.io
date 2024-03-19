package com.hm.blog.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLCommand extends BaseDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * 查询方法:
     */
    public ResultSet query(String sql, Object... args) {
        //连接对象
        connection = this.getConn();
        //执行者
        try {
            preparedStatement = connection.prepareStatement(sql);
            setArgs(preparedStatement, args);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void close() {
        super.close(this.resultSet, this.preparedStatement, this.connection);
    }

    /**
     * 为？赋值
     *
     * @param preparedStatement
     * @param args
     */
    private void setArgs(PreparedStatement preparedStatement, Object... args) {
        if (args == null)
            return;
        for (int i = 0; i < args.length; i++) {
            try {
                preparedStatement.setObject(i + 1, args[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 增、删除、改
     */
    public int execute(String sql, Object... args) {
        try {
            this.connection = this.getConn();
            this.preparedStatement = this.connection.prepareStatement(sql);
            this.setArgs(preparedStatement, args);
            return this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
        return 0;
    }

}
