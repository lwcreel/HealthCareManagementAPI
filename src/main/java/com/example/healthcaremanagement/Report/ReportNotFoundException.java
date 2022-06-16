package com.example.healthcaremanagement.Report;

public class ReportNotFoundException extends RuntimeException {

    ReportNotFoundException(Long id) {
        super("Could not find report " + id);
    }
}
