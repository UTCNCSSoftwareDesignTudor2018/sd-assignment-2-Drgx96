package com.assingment2.demo.rest.dtos;

import com.assingment2.demo.persistence.entities.Course;
import com.assingment2.demo.rest.dtos.CourseInfo;
import com.assingment2.demo.rest.dtos.StudentCourseAverage;

import java.util.List;

public class CourseReport {
    public CourseInfo course;
    public List<StudentCourseAverage> studentCourseAverageList;

    public CourseReport(){};

    public CourseReport(Course course, List<StudentCourseAverage> studentCourseAverageList) {
        this.course = new CourseInfo(course);
        this.studentCourseAverageList = studentCourseAverageList;
    }
}
