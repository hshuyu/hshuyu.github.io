package com.hm.blog.dao.impl;

import com.hm.blog.dao.IAricleAdminDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Article;
import com.hm.blog.entity.Category;
import com.hm.blog.entity.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 文章表增删改查
 */
public class ArticleAdminDao implements IAricleAdminDao {

    /**
     * 管理员表按条件查找
     * @param article
     * @return
     */
    @Override
    public Article queryByInfo(Article article) {
        Article article_=null;
        SQLCommand sqlCommand=new SQLCommand();
        String sql="select article1_code, article1_user_code, article1_title, article1_context,article1_status, article1_create_time, article1_update_time, article1_is_comment,article1_thumbnail,article1_category1_code from article_ where article1_code=?";
        ResultSet resultSet=sqlCommand.query(sql,article.getArticleCode());
        try {
            if (resultSet.next()){
                article_=new Article();
                article_.setArticleCode(resultSet.getInt("article1_code"));
                article_.setArticleUserCode(resultSet.getInt("article1_user_code"));
                article_.setArticleTitle(resultSet.getString("article1_title"));
                article_.setArticleContext(resultSet.getString("article1_context"));
                article_.setArticleStatus(resultSet.getInt("article1_status"));
                article_.setArticleCredate(resultSet.getString("article1_create_time"));
                article_.setArticleUpdate(resultSet.getString("article1_update_time"));
                article_.setArticleComment(resultSet.getInt("article1_is_comment"));
                article_.setArticleThumbnail(resultSet.getString("article1_thumbnail"));
                article_.setCategoryCode(resultSet.getInt("article1_category1_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article_;
    }

    @Override
    public List<Article> queryAll(Article article) {
        return null;
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Article> queryAll() {
        List<Article> articles=new ArrayList<>();
        SQLCommand sqlCommand=new SQLCommand();
        String sql="select article1_code, article1_user_code, article1_title, article1_context,article1_status, article1_create_time, article1_update_time, article1_is_comment ，article1_category1_code from article_";
        ResultSet resultSet=sqlCommand.query(sql);
        try {
            Article article_=null;
            while (resultSet.next()){
                article_=new Article();
                article_.setArticleCode(resultSet.getInt("article1_code"));
                article_.setArticleUserCode(resultSet.getInt("article1_user_code"));
                article_.setArticleTitle(resultSet.getString("article1_title"));
                article_.setArticleContext(resultSet.getString("article1_context"));
                article_.setArticleStatus(resultSet.getInt("article1_status"));
                article_.setArticleCredate(resultSet.getString("article1_create_time"));
                article_.setArticleUpdate(resultSet.getString("article1_update_time"));
                article_.setArticleComment(resultSet.getInt("article1_is_comment"));
                article_.setCategoryCode(resultSet.getInt("article1_category1_code"));
                articles.add(article_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    /**
     * 添加
     * @param article
     * @return
     */
    @Override
    public int insert(Article article) {
        String sql="insert into article_\n" +
                "  (article1_code, article1_user_code, article1_title, article1_context, article1_status, article1_create_time, article1_is_comment,article1_category1_code,article1_thumbnail)\n" +
                "values\n" +
                "  (BART.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,new Object[]{article.getArticleUserCode(),article.getArticleTitle(),article.getArticleContext()
        ,article.getArticleStatus(),article.getArticleCredate(),article.getArticleComment(),article.getCategoryCode(),article.getArticleThumbnail()});
        return res;
    }

    /**
     * 更新
     * @param article
     * @return
     */
    @Override
    public int update(Article article) {
        String sql="update article_\n" +
                "   set article1_user_code = ?,\n" +
                "       article1_title = ?,\n" +
                "       article1_context = ?,\n" +
                "       article1_status = ?,\n" +
                "       article1_create_time = ?,\n" +
                "       article1_update_time = ?,\n" +
                "       article1_is_comment = ?\n" +
                "       article1_category1_code = ?\n" +
                " where article1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,new Object[]{article.getArticleUserCode(),article.getArticleTitle(),article.getArticleContext()
               ,article.getArticleStatus(),article.getArticleCredate(),article.getArticleUpdate(),article.getArticleComment(),article.getCategoryCode(),article.getArticleCode()});
        return res;
    }

    /**
     * 删除
     * @param article
     * @return
     */
    @Override
    public int delete(Article article) {
        String sql="delete article_ where article1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,article.getArticleCode());
    }

    //加两条
    @Override
    public int queryCount(Article article) {
        String sql = "select count(article1_code) c from article_ ";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql);
        try {
            if (resultSet.next()){
                return resultSet.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<Article> queryAll(Article article, int page, int pageSize) {
        int start=(page-1)*pageSize;
        int end=page*pageSize;
        List<Article> articles = new Vector<>();
        String sql = "select * from\n" +
                "(select rownum r, a.article1_user_code, a.article1_code, u.user1_name, a.article1_title, a.article1_context, a.article1_status, a.article1_create_time, c.category1_name, a.article1_thumbnail\n" +
                " from (article_ a left join category_ c on c.category1_code=a.article1_category1_code)left join user_ u on u.user1_code=a.article1_user_code where rownum<=?) where r>? and article1_status!=3 order by article1_create_time desc";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql,end,start);
        try {
            Article article_ = null;
            while (resultSet.next()){
                article_ = new Article();
                article_.setArticleUserCode(resultSet.getInt("article1_user_code"));
                article_.setArticleCode(resultSet.getInt("article1_code"));
                article_.setUserName(resultSet.getString("user1_name"));
                article_.setArticleTitle(resultSet.getString("article1_title"));
                article_.setArticleContext(resultSet.getString("article1_context"));
                article_.setArticleStatus(resultSet.getInt("article1_status"));
                article_.setArticleCredate(resultSet.getString("article1_create_time"));
                article_.setCategoryName(resultSet.getString("category1_name"));
                article_.setArticleThumbnail(resultSet.getString("article1_thumbnail"));
                articles.add(article_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public int edit(Article article) {
        String sql="update article_\n" +
                "   set article1_status = ?\n" +
                " where article1_code = ?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql, article.getArticleStatus(),article.getArticleCode());
        return res;
    }

    @Override
    public List<Article> queryAlls(Article article, int page, int pageSize) {
        int start=(page-1)*pageSize;
        int end=page*pageSize;
        List<Article> articles = new Vector<>();
        String sql = "select * from\n" +
                "(select rownum r, a.article1_user_code, a.article1_code, u.user1_name, a.article1_title, a.article1_context, a.article1_status, a.article1_create_time, c.category1_name, a.article1_thumbnail\n" +
                " from (article_ a left join category_ c on c.category1_code=a.article1_category1_code)left join user_ u on u.user1_code=a.article1_user_code where a.article1_title=? and rownum<=?) where r>?";
        SQLCommand sqlCommand = new SQLCommand();
        ResultSet resultSet = sqlCommand.query(sql,article.getArticleTitle(),end,start);
        try {
            Article article_ = null;
            while (resultSet.next()){
                article_ = new Article();
                article_.setArticleUserCode(resultSet.getInt("article1_user_code"));
                article_.setArticleCode(resultSet.getInt("article1_code"));
                article_.setUserName(resultSet.getString("user1_name"));
                article_.setArticleTitle(resultSet.getString("article1_title"));
                article_.setArticleContext(resultSet.getString("article1_context"));
                article_.setArticleStatus(resultSet.getInt("article1_status"));
                article_.setArticleCredate(resultSet.getString("article1_create_time"));
                article_.setCategoryName(resultSet.getString("category1_name"));
                article_.setArticleThumbnail(resultSet.getString("article1_thumbnail"));
                articles.add(article_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public List<Category> categoryAll(Category category) {
        List<Category> categorys=new ArrayList<>();
        SQLCommand sqlCommand=new SQLCommand();
        String sql="select category1_code, category1_name, category1_parent_code from category_";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorys;
    }

    @Override
    public List<Tag> tagAll(Tag tag) {
        List<Tag> tags=new ArrayList<>();
        SQLCommand sqlCommand=new SQLCommand();
        String sql="select tag1_code, tag1_name from tag_";
        ResultSet resultSet=sqlCommand.query(sql);
        try {
            Tag tag_=null;
            while (resultSet.next()){
                tag_=new Tag();
                tag_.setTagCode(resultSet.getInt("tag1_code"));
                tag_.setTagName(resultSet.getString("tag1_name"));
                tags.add(tag_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

}
