package com.assingment2.demo.services;

import com.assingment2.demo.ReportBuilder;
import com.assingment2.demo.persistence.entities.Course;
import com.assingment2.demo.persistence.entities.Enrollment;
import com.assingment2.demo.persistence.entities.Grade;
import com.assingment2.demo.persistence.entities.Report;
import com.assingment2.demo.persistence.repos.CourseRepo;
import com.assingment2.demo.persistence.repos.IReportRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@Service
public class ReportService {

    @Inject
    IReportRepo reportRepo;

    @Inject
    private CourseRepo courseRepo;

    public void generateReport() {
        ReportBuilder reportBuilder = new ReportBuilder();
        List<Course> courses = courseRepo.findAll();
        for (Course c : courses) {
            Set<Enrollment> enrollments = c.getEnrollments();
            for (Enrollment e : enrollments) {
                reportBuilder.addRecord(c, e.getUser(), calculateAverage(e.getGrades()));
            }
        }
        reportRepo.store(reportBuilder.build());
    }

    private Float calculateAverage(Set<Grade> grades) {
        float sum = 0;
        int n = 0;
        for (Grade g: grades) {
            sum += g.getValue();
            n++;
        }
        return sum/n;
    }

    public Set<Report> getReports() {
        return reportRepo.retrieveReports();
    }
}
