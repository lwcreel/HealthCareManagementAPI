package com.example.healthcaremanagement.Exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find customer " + id);
    }
}
