package com.example.inmaculada.services;

import com.example.inmaculada.api.dto.ChangePasswordRequest;
import com.example.inmaculada.api.dto.UserDto;

import java.util.List;

public interface IUserServices {
    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    String deleteUser(Long id);

    List<UserDto> searchUsers(String firstName, String lastName, String email);

    void changePassword(ChangePasswordRequest changePasswordRequest, String userEmail);

    UserDto getUserByUserEmail(String email);
}
