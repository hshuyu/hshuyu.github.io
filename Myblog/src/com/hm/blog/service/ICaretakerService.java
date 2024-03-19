package com.hm.blog.service;

import com.hm.blog.entity.Caretaker;

public interface ICaretakerService {
    Caretaker adminLogin(Caretaker caretaker);

    Caretaker checkUserName(Caretaker caretaker);

    int caretakerEdit(Caretaker caretaker);

    Caretaker getCaretaker(Caretaker caretaker);

    int caretakerEdit1(Caretaker caretaker);

    //12
    Caretaker upStatus(Caretaker caretaker1);
    Caretaker downStatus(Caretaker caretaker1);
}
