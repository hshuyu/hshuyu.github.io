package com.hm.blog.dao;

import java.util.List;

public interface IBaseDao<T> {
    T queryByInfo(T  t);
    List<T> queryAll(T  t);
    int insert(T  t);
    int update(T  t);
    int delete(T  t);
}
