package com.example.healthcaremanagement.Exceptions;

public class MedicineNotFoundException extends RuntimeException {

    public MedicineNotFoundException(Long id) {
        super("Could not find medicine " + id);
    }
}
