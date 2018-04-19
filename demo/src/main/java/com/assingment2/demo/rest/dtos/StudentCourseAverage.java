package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.User;
import com.assingment2.demo.rest.dtos.StudentInfo;

public class StudentCourseAverage {
    public StudentInfo studentInfo;
    public Float average;

    public StudentCourseAverage() {
    };

    public StudentCourseAverage(User user, Float average) {
        this.studentInfo = new StudentInfo(user);
        this.average = average;
    }
}
