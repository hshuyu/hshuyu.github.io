package com.hm.blog.dao;


import com.hm.blog.entity.Caretaker;

public interface ICaretakerDao extends IBaseDao<Caretaker> {
    Caretaker queryByInfos(Caretaker caretaker);

    int update1(Caretaker caretaker);

    //12
    Caretaker upStatus(Caretaker caretaker1);
    Caretaker downStatus(Caretaker caretaker1);
}
