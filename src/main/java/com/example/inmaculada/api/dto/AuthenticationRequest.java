package com.example.inmaculada.api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequest {
    private String email;
    private String password;

}


