package com.example.healthcaremanagement.Exceptions;

public class ReportNotFoundException extends RuntimeException {

    ReportNotFoundException(Long id) {
        super("Could not find report " + id);
    }
}
