package com.example.inmaculada.api.dto;

import com.example.inmaculada.domain.enums.UserRol;
import lombok.Data;
import lombok.Setter;

@Data
public class AuthenticationResponse {
    private String jwt;

    private UserRol userRole;

    private Long userId;


}
