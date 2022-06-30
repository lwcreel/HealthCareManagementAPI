package com.example.healthcaremanagement.Report;

import com.example.healthcaremanagement.User.User;
import com.example.healthcaremanagement.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    ReportRepository repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/reports")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Report> getAllReports () {

        return repository.findAll();
    }

    @GetMapping(value = "/report")
    Report getReportByReportId(@RequestParam("id") Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    @GetMapping(value = "/user/reports")
    List<Report> getReportsByCustomerId(@RequestParam ("customerid") Long customerid) {

        List<Report> reports = repository.findAll();

        for(Report r : reports) {
            if (r.getCustomerId() != customerid)
                reports.remove(r);
        }
        return reports;
    }

    @PostMapping(value = "/report")
    Report createReport(@RequestBody Report newReport) {

        repository.save(newReport);

        return newReport;
    }

    @PutMapping(value = "/report")
    Report updateReportById(@RequestParam ("id") Long id, @RequestBody Report newReport) {

        return repository.findById(id)
                .map(report -> {
                    report.setCustomerId(newReport.getCustomerId());
                    return repository.save(report);
                }).orElseGet(() -> {
                    return repository.save(newReport);
                });
    }

    @DeleteMapping(value = "/reports")
    void deleteReportById(@RequestParam ("id") Long id) {

        repository.deleteById(id);
    }
}
