package com.neocortex.recognitioncortex.reponses;

import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private String account;
    private String username;
    private List<String> roles;

    public JwtResponse(String accessToken, String account, String username, List<String> roles) {
        this.token = accessToken;
        this.account = account;
        this.username = username;
        this.roles = roles;
    }
}

