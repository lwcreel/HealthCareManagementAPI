package com.example.healthcaremanagement.Payload.Responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
    @Getter @Setter private String token;
    @Getter @Setter private Long id;
    @Getter @Setter private String username;
    @Getter @Setter private String email;
    @Getter private List<String> roles;
    private String type = "Bearer";

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
