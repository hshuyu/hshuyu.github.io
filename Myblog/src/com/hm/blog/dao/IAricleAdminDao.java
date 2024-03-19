package com.hm.blog.dao;

import com.hm.blog.entity.Article;
import com.hm.blog.entity.Category;
import com.hm.blog.entity.Tag;

import java.util.List;

public interface IAricleAdminDao extends IBaseDao<Article>{
    List<Article> queryAll();

    //加两条
    int queryCount(Article article);
    List<Article> queryAll(Article article, int page, int pageSize);

    int edit(Article article);

    List<Article> queryAlls(Article article, int page, int pageSize);

    List<Category> categoryAll(Category category);

    List<Tag> tagAll(Tag tag);
}
