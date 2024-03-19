package com.hm.blog.entity;

/**
 * 记录表
 */
public class Record {
    private int recordCode;//记录编号
    private int userCode;//访问用户（外键）
    private String recordDate;//访问时间
    private int recordLove;//喜爱程度
    private int articleCode;//文章编号

    public Record() {
    }

    public int getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(int recordCode) {
        this.recordCode = recordCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getRecordLove() {
        return recordLove;
    }

    public void setRecordLove(int recordLove) {
        this.recordLove = recordLove;
    }

    public int getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(int articleCode) {
        this.articleCode = articleCode;
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordCode=" + recordCode +
                ", userCode=" + userCode +
                ", recordDate='" + recordDate + '\'' +
                ", recordLove=" + recordLove +
                ", articleCode=" + articleCode +
                '}';
    }
}