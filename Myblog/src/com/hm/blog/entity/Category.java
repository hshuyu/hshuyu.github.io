package com.hm.blog.entity;

/**
 * 类别表
 */
public class Category {
    private Integer categoryCode;//类别编号
    private String categoryName;//类别名称
    private Integer parentCode;//文章编号

    public Category() {
    }

    public Category(Integer categoryCode, String categoryName, Integer parentCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.parentCode = parentCode;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", parentCode=" + parentCode +
                '}';
    }
}
