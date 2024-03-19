package com.hm.blog.entity;

/**
 * 留言表
 */
public class Message {
    private Integer  messageCode;//留言编号
    private String  messageDate;//留言时间
    private Integer  userCode;//用户编号
    private String  messageContent;//留言内容
    private Integer  articleCode;//文章编号
    private Integer parentCode;//父留言编号
    public Message() {
    }

    public Message(Integer messageCode, String messageDate, Integer userCode, String messageContent, Integer articleCode,Integer parentCode) {
        this.messageCode = messageCode;
        this.messageDate = messageDate;
        this.userCode = userCode;
        this.messageContent = messageContent;
        this.articleCode = articleCode;
        this.parentCode=parentCode;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(Integer messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(Integer articleCode) {
        this.articleCode = articleCode;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageCode=" + messageCode +
                ", messageDate='" + messageDate + '\'' +
                ", userCode=" + userCode +
                ", messageContent='" + messageContent + '\'' +
                ", articleCode=" + articleCode +
                '}';
    }
}
