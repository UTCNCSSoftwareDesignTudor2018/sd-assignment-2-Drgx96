package com.assingment2.demo.persistence.repos;

import com.assingment2.demo.persistence.entities.Report;

import java.util.Set;

public interface IReportRepo {
    void store(Report report);

    Set<Report> retrieveReports();
}
