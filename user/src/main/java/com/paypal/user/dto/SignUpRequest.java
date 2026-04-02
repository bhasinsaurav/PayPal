package com.paypal.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SignUpRequest {
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private String password;
    private String adminKey;

    public SignUpRequest() {
        // default constructor
    }

    public SignUpRequest(String name, String email, String password, String adminKey) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.adminKey = adminKey;

    }

}
