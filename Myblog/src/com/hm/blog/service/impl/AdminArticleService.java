package com.hm.blog.service.impl;

import com.hm.blog.dao.IAricleAdminDao;
import com.hm.blog.dao.impl.ArticleAdminDao;
import com.hm.blog.entity.Article;
import com.hm.blog.entity.Category;
import com.hm.blog.entity.Tag;
import com.hm.blog.service.IAdminArticleService;
import com.hm.blog.util.PageUtils;

import java.util.List;

public class AdminArticleService implements IAdminArticleService {

    //数据层对象
    private IAricleAdminDao iAricleAdminDao;
    public AdminArticleService(){
        this.iAricleAdminDao = new ArticleAdminDao();
    }

    @Override
    public PageUtils<Article> getAllProduct(Article article, int page, int pageSize) {
        //总记录数
        int counts = this.iAricleAdminDao.queryCount(article);
        PageUtils<Article> pageUtil = new PageUtils<Article>(page,pageSize,counts);
        List<Article> articles = this.iAricleAdminDao.queryAll(article,page,pageSize);
        pageUtil.setDate(articles);
        return pageUtil;
    }

    @Override
    public Article getArticle(Article article) {
        return this.iAricleAdminDao.queryByInfo(article);
    }

    @Override
    public int articleEdit(Article article) {
        return this.iAricleAdminDao.edit(article);
    }

    @Override
    public int articleDelete(Article article) {
        return this.iAricleAdminDao.delete(article);
    }

    @Override
    public PageUtils<Article> getAllArticles(Article article, int page, int pageSize) {
        //总记录数
        int counts = this.iAricleAdminDao.queryCount(article);
        PageUtils<Article> pageUtil = new PageUtils<Article>(page,pageSize,counts);
        List<Article> articles = this.iAricleAdminDao.queryAlls(article,page,pageSize);
        pageUtil.setDate(articles);
        return pageUtil;
    }

    @Override
    public List<Category> getAllCategory(Category category) {
        return this.iAricleAdminDao.categoryAll(category);
    }

    @Override
    public List<Tag> getAllTag(Tag tag) {
        return this.iAricleAdminDao.tagAll(tag);
    }

    @Override
    public int articleInsert(Article article) {
        return this.iAricleAdminDao.insert(article);
    }


}
