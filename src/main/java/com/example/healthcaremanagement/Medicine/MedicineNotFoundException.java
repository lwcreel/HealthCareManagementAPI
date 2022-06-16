package com.example.healthcaremanagement.Medicine;

public class MedicineNotFoundException extends RuntimeException {

    MedicineNotFoundException(Long id) {
        super("Could not find medicine " + id);
    }
}
