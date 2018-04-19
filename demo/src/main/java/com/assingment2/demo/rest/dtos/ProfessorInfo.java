package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.User;

public class ProfessorInfo extends UserInfo {

    public ProfessorInfo(){};

    public ProfessorInfo(User user) {
        super(user);
    }
}
