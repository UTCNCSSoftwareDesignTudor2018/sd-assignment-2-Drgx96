package com.assingment2.demo.rest;

import com.assingment2.demo.persistence.entities.Report;
import com.assingment2.demo.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Set;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Inject
    ReportService reportService;

    @PostMapping
    public ResponseEntity<?> makeReport() {
        this.reportService.generateReport();
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public Set<Report> getReports() {
        return reportService.getReports();
    }
}
