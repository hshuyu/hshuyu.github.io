package com.hm.blog.dao;

import com.hm.blog.dao.IBaseDao;
import com.hm.blog.entity.User;

import java.util.List;

public interface IUserAdminDao extends IBaseDao<User> {

    int queryCount(User user);

    List<User> queryAll(User user, int page, int pageSize);

    int edit(User user);

    List<User> queryAlls(User user, int page, int pageSize);
}
