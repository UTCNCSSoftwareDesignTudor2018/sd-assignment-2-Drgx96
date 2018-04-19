package com.assingment2.demo;

import com.assingment2.demo.persistence.entities.*;
import com.assingment2.demo.rest.dtos.CourseReport;
import com.assingment2.demo.rest.dtos.StudentCourseAverage;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ReportBuilder {
    private HashMap<Integer, HashMap<Integer, Float>> averages;
    private HashMap<Integer, Course> courses;
    private HashMap<Integer, User> users;

    public ReportBuilder() {
        averages = new HashMap<>();
        courses = new HashMap<>();
        users = new HashMap<>();
    }

    public void addRecord(Course course, User user, Float average) {
        users.put(user.getId(), user);
        if (courses.containsKey(course.getId())) {
            averages.get(course.getId()).put(user.getId(), average);
        } else {
            courses.put(course.getId(), course);
            HashMap<Integer, Float> students = new HashMap<>();
            students.put(user.getId(), average);
            averages.put(course.getId(), students);
        }
    }

    public Report build() {
        Report report = new Report(
                System.currentTimeMillis(),
                courses.values().stream().map(this::getCourseReport).collect(Collectors.toList()));
        return report;
    }

    private CourseReport getCourseReport(Course y) {
        return new CourseReport(y, averages.get(y.getId()).entrySet().stream()
                .map(this::mapToStudentCourseAverage).collect(Collectors.toList()));
    }

    private StudentCourseAverage mapToStudentCourseAverage(Entry<Integer, Float> integerFloatEntry) {
        return new StudentCourseAverage(users.get(integerFloatEntry.getKey()), integerFloatEntry.getValue());
    }
}
