package com.hm.blog.service.impl;

import com.hm.blog.dao.IUserAdminDao;
import com.hm.blog.dao.impl.UserAdminAdminDao;
import com.hm.blog.entity.User;
import com.hm.blog.service.IAdminUserService;
import com.hm.blog.util.PageUtils;

import java.util.List;

public class AdminUserService implements IAdminUserService {
    //数据层对象
    private IUserAdminDao iUserAdminDao;
    public AdminUserService(){
        this.iUserAdminDao = new UserAdminAdminDao();
    }

    @Override
    public PageUtils<User> getAllUser(User user, int page, int pageSize) {
        //总记录数
        int counts = this.iUserAdminDao.queryCount(user);
        PageUtils<User> pageUtil = new PageUtils<User>(page,pageSize,counts);
        List<User> users = this.iUserAdminDao.queryAll(user,page,pageSize);
        pageUtil.setDate(users);
        return pageUtil;
    }

    @Override
    public User getUser(User user) {
        return this.iUserAdminDao.queryByInfo(user);
    }

    @Override
    public int userEdit(User user) {
        return this.iUserAdminDao.edit(user);
    }

    @Override
    public int userDelete(User user) {
        return this.iUserAdminDao.delete(user);
    }

    @Override
    public PageUtils<User> getAllUsers(User user, int page, int pageSize) {
        //总记录数
        int counts = this.iUserAdminDao.queryCount(user);
        PageUtils<User> pageUtil = new PageUtils<User>(page,pageSize,counts);
        List<User> users = this.iUserAdminDao.queryAlls(user,page,pageSize);
        pageUtil.setDate(users);
        return pageUtil;
    }

    @Override
    public int userInsert(User user) {
        return this.iUserAdminDao.insert(user);
    }
}
