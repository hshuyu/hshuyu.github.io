package com.hm.blog.dao.impl;

import com.hm.blog.dao.IMessageDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 留言表增删改查
 */
public class MessageDao implements IMessageDao {

    /**
     * 通过条件查询留言
     * @param message
     * @return
     */
    @Override
    public Message queryByInfo(Message message) {

        Message message_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select message1_code, message1_date, message1_user_code, message1_content, message1_article_code,message1_parent_code from message_ where message1_code=?";
        ResultSet resultSet = sqlCommand.query(sql, message.getMessageCode());
        try {
            if (resultSet.next()) {
                message_ = new Message();
                message_.setMessageDate(resultSet.getString("message1_date"));
                message_.setUserCode(resultSet.getInt("message1_user_code"));
                message_.setMessageContent(resultSet.getString("message1_content"));
                message_.setArticleCode(resultSet.getInt("message1_article_code"));
                message_.setParentCode(resultSet.getInt("message1_parent_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return message_;
    }

    /**
     * 查询所有留言
     * @param message
     * @return
     */

    @Override
    public List<Message> queryAll(Message message) {
        List<Message> messages=new Vector<>();
        String sql="select message1_code, message1_date, message1_user_code, message1_content, message1_article_code,message1_parent_code from message_";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet=sqlCommand.query(sql);

        try {
            Message message_=null;
            while (resultSet.next()){
                message_=new Message();
                message_.setMessageDate(resultSet.getString("message1_date"));
                message_.setUserCode(resultSet.getInt("message1_user_code"));
                message_.setMessageContent(resultSet.getString("message1_content"));
                message_.setArticleCode(resultSet.getInt("message1_article_code"));
                message_.setParentCode(resultSet.getInt("message1_parent_code"));
                messages.add(message_);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return messages;
    }

    /**
     * 增加留言
     * @param message
     * @return
     */
    @Override
    public int insert(Message message) {
        String sql="insert into message_ (message1_code, message1_date, message1_user_code, message1_content, message1_article_code,message1_parent_code)\n" +
                " values (BMG.nextval,?,?,?,?,?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,message.getMessageDate(),message.getUserCode(),message.getMessageContent(),message.getArticleCode(),message.getParentCode());
        return res;
    }

    /**
     * 修改留言
     * @param message
     * @return
     */
    @Override
    public int update(Message message) {
        String sql="update  message_  set message1_date=?,message1_content=?,message1_parent_code=? where  message1_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,message.getMessageDate(),message.getMessageContent(),message.getParentCode(),message.getMessageCode());
        return res;
    }

    /**
     * 删除留言
     * @param message
     * @return
     */
    @Override
    public int delete(Message message) {
        String sql="delete message_ where message1_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,message.getMessageCode());
    }
}
