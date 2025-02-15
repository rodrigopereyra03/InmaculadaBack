package com.example.inmaculada.api.mappers;

import com.example.inmaculada.api.dto.UserDto;
import com.example.inmaculada.domain.models.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDateCreated(userDto.getDateCreated());
        user.setDocumentNumber(userDto.getDocumentNumber());
        user.setPhone(userDto.getPhone());
        user.setRol(userDto.getRol());
        return user;
    }

    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setDateCreated(user.getDateCreated());
        userDto.setDocumentNumber(user.getDocumentNumber());
        userDto.setPhone(user.getPhone());
        userDto.setRol(user.getRol());
        return userDto;
    }
}
