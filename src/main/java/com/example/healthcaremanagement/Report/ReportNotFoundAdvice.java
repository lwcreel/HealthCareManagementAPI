package com.example.healthcaremanagement.Report;

import com.example.healthcaremanagement.Medicine.MedicineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ReportNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reportNotFoundHandler(ReportNotFoundException ex) {
        return ex.getMessage();
    }
}
