package com.example.inmaculada.api.dto;

import com.example.inmaculada.domain.models.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SingupRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int documentNumber;
    private int phone;
    private Address address;
    private LocalDateTime dateCreated;
}
