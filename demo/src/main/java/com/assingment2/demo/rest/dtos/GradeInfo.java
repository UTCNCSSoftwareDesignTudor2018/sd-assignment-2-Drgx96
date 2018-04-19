package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.Grade;

import java.sql.Date;

public class GradeInfo {
    public Date date;

    public String evaluationType;

    public Double value;

    public GradeInfo(){};

    public GradeInfo(Grade grade) {
        date = grade.getDate();
        evaluationType = grade.getEvaluationType();
        value = grade.getValue();
    }

    public Grade toGrade() {
        Grade grade = new Grade();
        grade.setDate(date);
        grade.setEvaluationType(evaluationType);
        grade.setValue(value);
        return grade;
    }
}
