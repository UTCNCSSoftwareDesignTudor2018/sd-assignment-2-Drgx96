package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.Enrollment;

import java.util.Set;
import java.util.stream.Collectors;

public class EnrollmentInfo {
    public CourseInfo course;
    public Set<GradeInfo> grades;

    public EnrollmentInfo(){};

    public EnrollmentInfo(Enrollment enrollment) {
        grades = enrollment.getGrades().stream().map(GradeInfo::new).collect(Collectors.toSet());
        course = new CourseInfo(enrollment.getCourse());
    }
}
