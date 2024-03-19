package com.hm.blog.entity;

/**
 * 文章标签表
 */
public class Article_Tag {
    private int A_TagCode;//文章标签编号
    private  int A_T_TagCode;//标签编号
    private  int A_T_ArticleCode;//文章编号

public Article_Tag(){

}
    public Article_Tag(int a_TagCode, int a_T_TagCode, int a_T_ArticleCode) {
        A_TagCode = a_TagCode;
        A_T_TagCode = a_T_TagCode;
        A_T_ArticleCode = a_T_ArticleCode;
    }

    public int getA_TagCode() {
        return A_TagCode;
    }

    public void setA_TagCode(int a_TagCode) {
        A_TagCode = a_TagCode;
    }

    public int getA_T_TagCode() {
        return A_T_TagCode;
    }

    public void setA_T_TagCode(int a_T_TagCode) {
        A_T_TagCode = a_T_TagCode;
    }

    public int getA_T_ArticleCode() {
        return A_T_ArticleCode;
    }

    public void setA_T_ArticleCode(int a_T_ArticleCode) {
        A_T_ArticleCode = a_T_ArticleCode;
    }
}
