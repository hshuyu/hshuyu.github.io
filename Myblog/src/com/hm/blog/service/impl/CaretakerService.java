package com.hm.blog.service.impl;

import com.hm.blog.dao.ICaretakerDao;
import com.hm.blog.dao.impl.CaretakerDao;
import com.hm.blog.entity.Caretaker;
import com.hm.blog.service.ICaretakerService;

public class CaretakerService implements ICaretakerService {

    private ICaretakerDao iCaretakerDao;

    public CaretakerService(){
        this.iCaretakerDao = new CaretakerDao();
    }

    @Override
    public Caretaker adminLogin(Caretaker caretaker) {
        //提取密码
        String password = caretaker.getCaretakerPwd();
        //访问数据库 jdbc 数据层对象
        caretaker = this.iCaretakerDao.queryByInfos(caretaker);
        if (caretaker!=null){
            //密码比较
            if (password.equals(caretaker.getCaretakerPwd())){
                return caretaker;
            }
        }
        return null;
    }

    @Override
    public Caretaker checkUserName(Caretaker caretaker) {
        caretaker = this.iCaretakerDao.queryByInfos(caretaker);
        return caretaker;
    }

    @Override
    public int caretakerEdit(Caretaker caretaker) {
        return this.iCaretakerDao.update(caretaker);
    }

    @Override
    public Caretaker getCaretaker(Caretaker caretaker) {
        return this.iCaretakerDao.queryByInfo(caretaker);
    }

    @Override
    public int caretakerEdit1(Caretaker caretaker) {
        return this.iCaretakerDao.update1(caretaker);
    }

    //12
    @Override
    public Caretaker upStatus(Caretaker caretaker1) {
        return this.iCaretakerDao.upStatus(caretaker1);
    }
    @Override
    public Caretaker downStatus(Caretaker caretaker1) {
        return this.iCaretakerDao.upStatus(caretaker1);
    }
}
