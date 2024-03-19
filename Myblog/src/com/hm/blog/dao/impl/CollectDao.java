package com.hm.blog.dao.impl;

import com.hm.blog.dao.ICollectDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Collect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class CollectDao implements ICollectDao {
    /**
     * 通过条件查询收藏
     * @param collect
     * @return
     */
    @Override
    public Collect queryByInfo(Collect collect) {
        Collect collect_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select collect1_code, collect1_user_code, collect1_date, collect1_article_code from collect_ where collect1_code=?";
        ResultSet resultSet = sqlCommand.query(sql, collect.getCollectCode());
        try {
            if (resultSet.next()) {
                collect_ = new Collect();
                collect_.setCollectCode(collect.getCollectCode());
                collect_.setUserCode(resultSet.getInt("collect1_user_code"));
                collect_.setCollectDate(resultSet.getString("collect1_date"));
                collect_.setArticle_code(resultSet.getInt("collect1_article_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return collect_;
    }

    /**
     * 查询所有收藏
     * @param collect
     * @return
     */
    @Override
    public List<Collect> queryAll(Collect collect) {
        List<Collect> collects=new Vector<>();
        String sql="select collect1_code, collect1_user_code, collect1_date, collect1_article_code from collect_";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet=sqlCommand.query(sql);

        try {
           Collect collect_=null;
            while (resultSet.next()){
                collect_ = new Collect();
                collect_.setCollectCode(resultSet.getInt("collect1_code"));
                collect_.setUserCode(resultSet.getInt("collect1_user_code"));
                collect_.setCollectDate(resultSet.getString("collect1_date"));
                collect_.setArticle_code(resultSet.getInt("collect1_article_code"));
                collects.add(collect_);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return collects;
    }

    /**
     * 增加收藏
     * @param collect
     * @return
     */
    @Override
    public int insert(Collect collect) {
        String sql="insert into collect_ (collect1_code, collect1_user_code, collect1_date, collect1_article_code) values (BCO.nextval,?,?,?)" ;
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,collect.getUserCode(),collect.getCollectDate(),collect.getArticle_code());
        return res;
    }

    /**
     * 更新收藏表
     * @param collect
     * @return
     */
    @Override
    public int update(Collect collect) {
        String sql="update collect_  set  collect1_user_code = ?, collect1_date = ?, collect1_article_code = ? where collect1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,collect.getUserCode(),collect.getCollectDate(),collect.getArticle_code(),collect.getCollectCode());
        return res;
    }

    /**
     * 删除收藏
     * @param collect
     * @return
     */
    @Override
    public int delete(Collect collect) {
        String sql="delete collect_ where collect1_code =?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,collect.getCollectCode());
    }
}
