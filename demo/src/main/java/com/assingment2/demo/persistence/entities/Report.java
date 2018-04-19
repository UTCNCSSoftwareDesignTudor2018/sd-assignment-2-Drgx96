package com.assingment2.demo.persistence.entities;

import com.assingment2.demo.rest.dtos.CourseReport;

import java.util.List;

public class Report {
    public Long timestamp;
    public List<CourseReport> courseReports;

    public Report() {};

    public Report(Long timestamp, List<CourseReport> courseReports) {
        this.timestamp = timestamp;
        this.courseReports = courseReports;
    }
}
