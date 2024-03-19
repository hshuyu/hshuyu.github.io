package com.hm.blog.entity;

/***
 * 标签表数据
 * tag1_code int not null,--标签编号
 * tag1_name varchar(50),--标签名称
 */
public class Tag {
    private Integer tagCode;
    private String tagName;


    public Tag(){
    }

    public Tag(Integer tagCode, String tagName) {
        this.tagCode = tagCode;
        this.tagName = tagName;
    }

    public Integer getTagCode() {
        return tagCode;
    }

    public void setTagCode(Integer tagCode) {
        this.tagCode = tagCode;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

}
