package com.example.inmaculada.services;

import com.example.inmaculada.api.dto.SingupRequest;
import com.example.inmaculada.api.dto.UserDto;

public interface IAuthServices {
    UserDto createUser(SingupRequest singupRequest);

    void resetPassword(String email);
}
