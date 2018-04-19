package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.User;

public class StudentInfo extends UserInfo{

    public StudentInfo() {}

    public StudentInfo(User user) {
        super(user);
    }
}
