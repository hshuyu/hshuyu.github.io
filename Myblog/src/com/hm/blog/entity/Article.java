package com.hm.blog.entity;

/**
 * 文章表
 */
public class Article {
    private Integer articleCode;//文章编号
    private Integer articleUserCode;//用户编号
    private String articleTitle;//标题
    private String articleContext;//内容
    private Integer articleStatus;//审核状态
    private String articleCredate;//创建时间
    private String articleUpdate;//更新日期
    private Integer articleComment;//是否能评论
    private Integer categoryCode;//类别编号
    private String articleThumbnail;
    private String userName;
    private String categoryName;
    public  Article(){}

    public Article(Integer articleCode, Integer articleUserCode, String articleTitle, String articleContext, Integer articleStatus, String articleCredate, String articleUpdate, Integer articleComment, Integer categoryCode, String userName, String categoryName, String articleThumbnail) {
        this.articleCode = articleCode;
        this.articleUserCode = articleUserCode;
        this.articleTitle = articleTitle;
        this.articleContext = articleContext;
        this.articleStatus = articleStatus;
        this.articleCredate = articleCredate;
        this.articleUpdate = articleUpdate;
        this.articleComment = articleComment;
        this.categoryCode = categoryCode;
        this.userName = userName;
        this.categoryName = categoryName;
        this.articleThumbnail = articleThumbnail;
    }

    public String getArticleThumbnail() {
        return articleThumbnail;
    }

    public void setArticleThumbnail(String articleThumbnail) {
        this.articleThumbnail = articleThumbnail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(Integer articleCode) {
        this.articleCode = articleCode;
    }

    public Integer getArticleUserCode() {
        return articleUserCode;
    }

    public void setArticleUserCode(Integer articleUserCode) {
        this.articleUserCode = articleUserCode;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContext() {
        return articleContext;
    }

    public void setArticleContext(String articleContext) {
        this.articleContext = articleContext;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleCredate() {
        return articleCredate;
    }

    public void setArticleCredate(String articleCredate) {
        this.articleCredate = articleCredate;
    }

    public String getArticleUpdate() {
        return articleUpdate;
    }

    public void setArticleUpdate(String articleUpdate) {
        this.articleUpdate = articleUpdate;
    }

    public Integer getArticleComment() {
        return articleComment;
    }

    public void setArticleComment(Integer articleComment) {
        this.articleComment = articleComment;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleCode=" + articleCode +
                ", articleUserCode=" + articleUserCode +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContext='" + articleContext + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleCredate='" + articleCredate + '\'' +
                ", articleUpdate='" + articleUpdate + '\'' +
                ", articleThumbnail='" + articleThumbnail + '\'' +
                ", articleComment=" + articleComment +
                '}';
    }
}
