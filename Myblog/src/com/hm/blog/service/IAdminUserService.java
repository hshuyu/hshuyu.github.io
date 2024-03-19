package com.hm.blog.service;

import com.hm.blog.entity.User;
import com.hm.blog.util.PageUtils;

public interface IAdminUserService {
    PageUtils<User> getAllUser(User user, int page, int pageSize);

    User getUser(User user);

    int userEdit(User user);

    int userDelete(User user);

    PageUtils<User> getAllUsers(User user, int page, int pageSize);

    int userInsert(User user);
}
