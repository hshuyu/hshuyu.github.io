package com.hm.blog.service;

import com.hm.blog.entity.Article;
import com.hm.blog.entity.Category;
import com.hm.blog.entity.Tag;
import com.hm.blog.util.PageUtils;

import java.util.List;

public interface IAdminArticleService {
    PageUtils<Article> getAllProduct(Article article, int page, int pageSize);

    Article getArticle(Article article);

    int articleEdit(Article article);

    int articleDelete(Article article);

    PageUtils<Article> getAllArticles(Article article, int page, int pageSize);

    List<Category> getAllCategory(Category category);

    List<Tag> getAllTag(Tag tag);

    int articleInsert(Article article);
}
