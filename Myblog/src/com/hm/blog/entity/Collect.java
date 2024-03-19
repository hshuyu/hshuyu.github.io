package com.hm.blog.entity;

/**
 * 收藏表
 */
public class Collect {
    private  int collectCode;//收藏编号
    private int userCode;//收藏用户
    private String collectDate;//收藏时间
    private int article_code;//收藏文章

    public int getCollectCode() {
        return collectCode;
    }

    public void setCollectCode(int collectCode) {
        this.collectCode = collectCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(String collectDate) {
        this.collectDate = collectDate;
    }

    public int getArticle_code() {
        return article_code;
    }

    public void setArticle_code(int article_code) {
        this.article_code = article_code;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectCode=" + collectCode +
                ", userCode=" + userCode +
                ", collectDate='" + collectDate + '\'' +
                ", article_code=" + article_code +
                '}';
    }
}
