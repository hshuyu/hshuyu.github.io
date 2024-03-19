package com.hm.blog.test;

import com.hm.blog.dao.impl.ArticleAdminDao;
import com.hm.blog.entity.Article;

public class Test {
    public static void main(String[] args) {
        ArticleAdminDao articleDao=new ArticleAdminDao();
//        List<Article> articles=articleDao.queryAll();
//            System.out.println(articles);

        Article article=new Article();
//        article.setArticleCode(1);
//        int res=articleDao.delete(article);
//        System.out.println(res);

//        article.setCommentCount(10);
//        article.setArticleUserCode(1);
//        article.setArticleTitle("啊圣诞袜");
//        article.setArticleContext("dawdasdasd");
//        article.setLikeCount(100);
//        int res=articleDao.insert(article);
//        System.out.println(res);


        article.setArticleUserCode(1);
        article.setArticleTitle("aaaa");
        article.setArticleContext("dddd");
        article.setArticleCode(2);
        int res=articleDao.update(article);
        System.out.println(res);
    }

}
