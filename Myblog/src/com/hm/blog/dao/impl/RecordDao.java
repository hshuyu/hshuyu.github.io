package com.hm.blog.dao.impl;

import com.hm.blog.dao.IRecordDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Record;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RecordDao implements IRecordDao {
    /**
     * 通过条件查询记录
     * @param record
     * @return
     */
    @Override
    public Record queryByInfo(Record record) {
        Record record_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select record1_code, record1_user_code, record1_date, record1_love, record1_article_code from record_ where record1_code=?";
        ResultSet resultSet = sqlCommand.query(sql, record.getRecordCode());
        try {
            if (resultSet.next()) {
                record_ = new Record();
                record_.setRecordCode(record.getRecordCode());
                record_.setUserCode(resultSet.getInt("record1_user_code"));
                record_.setRecordDate(resultSet.getString("record1_date"));
                record_.setRecordLove(resultSet.getInt("record1_love"));
                record_.setArticleCode(resultSet.getInt("record1_article_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return record_;
    }

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    @Override
    public List<Record> queryAll(Record record) {
        List<Record> records=new Vector<>();
        String sql="select record1_code, record1_user_code, record1_date, record1_love, record1_article_code from record_";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet=sqlCommand.query(sql);

        try {
            Record record_=null;
            while (resultSet.next()){
                record_ = new Record();
                record_.setRecordCode(resultSet.getInt("record1_code"));
                record_.setUserCode(resultSet.getInt("record1_user_code"));
                record_.setRecordDate(resultSet.getString("record1_date"));
                record_.setRecordLove(resultSet.getInt("record1_love"));
                record_.setArticleCode(resultSet.getInt("record1_article_code"));
                records.add(record_);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return records;
    }

    /**
     * 增加记录
     * @param record
     * @return
     */
    @Override
    public int insert(Record record) {
        String sql="insert into record_\n" +
                "  (record1_code, record1_user_code, record1_date, record1_love, record1_article_code)\n" +
                "values(BR.nextval,?,?,?,?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,record.getUserCode(),record.getRecordDate(),record.getRecordLove(),record.getArticleCode());
        return res;
    }

    /**
     * 更新记录表
     * @param record
     * @return
     */
    @Override
    public int update(Record record) {
        String sql="update record_ set record1_date =?,record1_love =? where record1_code =?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,record.getRecordDate(),record.getRecordLove(),record.getRecordCode());
        return res;
    }

    /**
     * 删除记录
     * @param record
     * @return
     */
    @Override
    public int delete(Record record) {
        String sql="delete record_ where record1_code =?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,record.getRecordCode());
    }
}
