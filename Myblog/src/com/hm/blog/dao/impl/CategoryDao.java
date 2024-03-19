package com.hm.blog.dao.impl;

import com.hm.blog.dao.ICategoryDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * 类别增删改查
 */
public class CategoryDao implements ICategoryDao {
    /**
     * 按条件查询
     * @param category
     * @return
     */
    @Override
    public Category queryByInfo(Category category) {
        Category category_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select category1_code, category1_name, category1_parent_code from category_ where category1_code=?";
        ResultSet resultSet = sqlCommand.query(sql, category.getCategoryCode());
        try {
            if (resultSet.next()) {
                category_ = new Category();
                category_.setCategoryCode(category.getCategoryCode());
                category_.setCategoryName(resultSet.getString("category1_name"));
                category_.setParentCode(resultSet.getInt("category1_parent_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return category_;
    }

    /**
     * 查询所有
     * @param category
     * @return
     */
    @Override
    public List<Category> queryAll(Category category) {
        List<Category> categorys=new Vector<>();
        String sql="select category1_code, category1_name, category1_parent_code from category_";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet=sqlCommand.query(sql);

        try {
            Category category_=null;
            while (resultSet.next()){
                category_=new Category();

                category_.setCategoryCode(resultSet.getInt("category1_code"));
                category_.setCategoryName(resultSet.getString("category1_name"));
                category_.setParentCode(resultSet.getInt("category1_parent_code"));
                categorys.add(category_);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categorys;
    }

    /**
     * 添加
     * @param category
     * @return
     */
    @Override
    public int insert(Category category) {
        String sql="insert into category_ (category1_code,category1_name,category1_parent_code)\n" +
                "       values (BCATE.nextval,?,?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,category.getCategoryName(),category.getParentCode());
        return res;
    }

    /**
     * 更新
     * @param category
     * @return
     */
    @Override
    public int update(Category category) {
        String sql="update  category_  set category1_name=?,category1_parent_code=? where  category1_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,category.getCategoryName(),category.getParentCode(),category.getCategoryCode());
        return res;
    }

    /**
     * 删除
     * @param category
     * @return
     */
    @Override
    public int delete(Category category) {
        String sql="delete category_ where category1_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,category.getCategoryCode());
    }
}
