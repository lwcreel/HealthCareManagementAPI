package com.example.healthcaremanagement.Payload.Requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class RegisterRequest {

    @NotBlank @Size(min = 3, max = 20) @Getter @Setter private String username;
    @NotBlank @Size(max = 50) @Email @Getter @Setter private String email;
    @NotBlank @Size(min = 6, max = 40) @Getter @Setter private String password;
    @NotBlank @Getter @Setter private String firstname;
    @NotBlank @Getter @Setter private String lastname;
    @NotBlank @Getter @Setter private long phonenumber;
    @Getter @Setter private Set<String> role;
}
