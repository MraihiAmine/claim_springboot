package com.youtube.jwt.entity;

import lombok.Data;

@Data
public class JwtResponse {

    private User user;
    private String jwtToken;
    private Navigation navigation;

    public JwtResponse(User user, String jwtToken, Navigation navigation) {
        this.user = user;
        this.jwtToken = jwtToken;
        this.navigation = navigation;
    }
}
