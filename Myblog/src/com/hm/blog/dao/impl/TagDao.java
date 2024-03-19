package com.hm.blog.dao.impl;


import com.hm.blog.dao.ITagDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 标签表
 */
public class TagDao implements ITagDao {
    /**
     * 按条件查询
     * @param tag
     * @return
     */
    @Override
    public Tag queryByInfo(Tag tag) {
        Tag tag_ = null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select tag1_code, tag1_name from tag_ where tag1_code=?";
        ResultSet resultSet = sqlCommand.query(sql,tag.getTagCode());
        try {
            if (resultSet.next()){
                tag_ = new Tag();
                tag_.setTagCode(resultSet.getInt("tag1_code"));
                tag_.setTagName(resultSet.getString("tag1_name"));

            }
            return tag_;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }
        return null;
    }

    /**
     * 查询所有
     * @param tag
     * @return
     */
    @Override
    public List<Tag> queryAll(Tag tag) {
        List<Tag> tags = new Vector<>();
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select tag1_code, tag1_name from tag_";
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            Tag tag_ = null;
            while (resultSet.next()){
                tag_ = new Tag();
                tag_.setTagCode(resultSet.getInt("tag1_code"));
                tag_.setTagName(resultSet.getString("tag1_name"));
                tags.add(tag_);
            }
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param tag
     * @return
     */
    @Override
    public int insert(Tag tag) {
        String sql = "insert into tag_(tag1_code, tag1_name) values(BTAG.nextval,?)";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,tag.getTagCode(),tag.getTagName());
        return res;
    }

    /**
     * 更新
     * @param tag
     * @return
     */
    @Override
    public int update(Tag tag) {
        String sql = "update tag_ set tag1_name=? where tag1_code=?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,tag.getTagName(),tag.getTagCode());
        return res;
    }

    /**
     * 删除
     * @param tag
     * @return
     */
    @Override
    public int delete(Tag tag) {
        String sql = "delete tag_ where tag1_code=?";
        SQLCommand sqlCommand = new SQLCommand();
        int res = sqlCommand.execute(sql,tag.getTagCode());
        return res;
    }
}
