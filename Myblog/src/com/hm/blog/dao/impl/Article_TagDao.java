package com.hm.blog.dao.impl;

import com.hm.blog.dao.IArticle_TagDao;
import com.hm.blog.dao.util.SQLCommand;
import com.hm.blog.entity.Article_Tag;
import com.hm.blog.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/*
create table article_tag(
article_tag_code int not null,--编号
article_tag_tag1_code int not null,--标签编号（外键）
article_tag_article1_code int not null--文章编号（外键）

)
--设置编号为主键
alter table article_tag add constraint pk_article_tag_code
primary key (article_tag_code)
--指定表中标签编号与标签表的标签编号对应
alter table article_tag add constraint
fk_tag1_code foreign key(article_tag_tag1_code) references
tag_(tag1_code);
--指定表中文章编号与文章表的文章编号对应
alter table article_tag add constraint
fk_article_tag_article1_code foreign key(article_tag_article1_code) references
article_(article1_code);
 */
public class Article_TagDao implements IArticle_TagDao {
    @Override
    public Article_Tag queryByInfo(Article_Tag article_tag) {
        Article_Tag article_tag1_= null;
        SQLCommand sqlCommand = new SQLCommand();
        String sql = "select article_tag_code, article_tag_tag1_code, article_tag_article1_code from article_tag where article_tag_code=?";
        ResultSet resultSet = sqlCommand.query(sql, article_tag1_.getA_TagCode());
        try {
            if (resultSet.next()) {
               article_tag1_ = new Article_Tag();
                article_tag1_.setA_TagCode(article_tag.getA_TagCode());
                article_tag1_.setA_T_TagCode(resultSet.getInt("article_tag_tag1_code"));
               article_tag1_.setA_T_ArticleCode(resultSet.getInt("article_tag_article1_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            sqlCommand.close();
        }

        return article_tag1_;
    }

    @Override
    public List<Article_Tag> queryAll(Article_Tag article_tag) {
        List<Article_Tag> article_tags=new Vector<>();
        String sql="select article_tag_code, article_tag_tag1_code, article_tag_article1_code from article_tag";
        SQLCommand sqlCommand=new SQLCommand();
        ResultSet resultSet=sqlCommand.query(sql);

        try {
            Article_Tag article_tag1_=null;
            while (resultSet.next()){
                article_tag1_= new Article_Tag();

                article_tag1_.setA_TagCode(resultSet.getInt("article_tag_code"));
                article_tag1_.setA_T_TagCode(resultSet.getInt("article_tag_tag1_code"));
                article_tag1_.setA_T_ArticleCode(resultSet.getInt("article_tag_article1_code"));
                article_tags.add(article_tag1_);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return article_tags;
    }

    @Override
    public int insert(Article_Tag article_tag) {
        String sql="insert into article_tag (article_tag_code,article_tag_tag1_code,article_tag_article1_code)\n" +
                "       values (BAT.nextval,?,?)";
        SQLCommand sqlCommand=new SQLCommand();
        int res= sqlCommand.execute(sql,article_tag.getA_T_TagCode(),article_tag.getA_T_ArticleCode());
        return res;
    }

    @Override
    public int update(Article_Tag article_tag) {
        String sql="update  article_tag   set article_tag_tag1_code=?,article_tag_article1_code=? where  article_tag_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        int res=sqlCommand.execute(sql,article_tag.getA_T_TagCode(),article_tag.getA_T_ArticleCode(),article_tag.getA_TagCode());
        return res;
    }

    @Override
    public int delete(Article_Tag article_tag) {
        String sql="delete  article_tag where article_tag_code=?";
        SQLCommand sqlCommand=new SQLCommand();
        return sqlCommand.execute(sql,article_tag.getA_TagCode());
    }
}
