package com.hm.blog.util;

import java.util.List;

public class PageUtils<T>{
    private List<T> data;
    //当前页
    private int page;
    //上一页
    private int prePage;
    //下一页
    private int nextPage;
    //总记录数
    private int totalRecord;
    //总页数
    private int totalPage;
    //单页面记录数
    private int pageSize;

    public PageUtils(int page, int pageSize, int totalRecord){
        this.page = page;
        this.totalRecord = totalRecord;
        this.pageSize = pageSize;
        this.prePage = this.page==1?this.page:this.page-1;
        this.totalPage = this.totalRecord%this.pageSize==0?this.totalRecord/this.pageSize:this.totalRecord/this.pageSize+1;
        this.nextPage = this.page==this.totalPage?this.page:this.page+1;
    }

    public void setDate(List<T> data){
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public int getPrePage() {
        return prePage;
    }

    public int getNextPage() {
        return nextPage;
    }
}
