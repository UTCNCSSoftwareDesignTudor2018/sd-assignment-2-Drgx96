package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseInfo {
    public Integer id;

    public String subject;

    public ProfessorInfo professor;

    public  List<StudentInfo> enrolledStudents;

    public CourseInfo() {};

    public CourseInfo(Course course) {
        id = course.getId();
        subject = course.getSubject();
        professor = new ProfessorInfo(course.getProfessor());
        enrolledStudents = course.getEnrollments().stream().map(x -> new StudentInfo(x.getUser())).collect(Collectors.toList());
    }

    public Course toCourse() {
        Course course = new Course();
        course.setId(id);
        course.setSubject(subject);
        return course;
    }
}
