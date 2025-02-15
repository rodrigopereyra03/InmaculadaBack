package com.example.inmaculada.services.impl;

import com.example.inmaculada.api.dto.SingupRequest;
import com.example.inmaculada.api.dto.UserDto;
import com.example.inmaculada.domain.enums.UserRol;
import com.example.inmaculada.domain.exceptions.UserNotFoundException;
import com.example.inmaculada.domain.models.User;
import com.example.inmaculada.repositories.sql.IUserRepositorySql;
import com.example.inmaculada.services.IAuthServices;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements IAuthServices {

    private final IUserRepositorySql userSQLRepository;

    public AuthServiceImpl(IUserRepositorySql userSQLRepository) {
        this.userSQLRepository = userSQLRepository;
    }


    //@PostConstruct
    public void createAdminAccount(){
        User user = new User();
        user.setFirstName("admin");
        user.setEmail("rodrigopereyra02@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("1234"));
        user.setRol(UserRol.ADMIN);
        userSQLRepository.save(user);

    }

    @Override
    public UserDto createUser(SingupRequest singupRequest) {
        boolean emailExists = userSQLRepository.findFirstByEmail(singupRequest.getEmail()).isPresent();

        User user = new User();
        user.setFirstName(singupRequest.getName());
        user.setLastName(singupRequest.getLastName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setDocumentNumber(singupRequest.getDocumentNumber());
        user.setAddresses(Collections.singletonList(singupRequest.getAddress()));
        user.setPhone(singupRequest.getPhone());
        user.setDateCreated(LocalDateTime.now());
        user.setRol(UserRol.CUSTOMER);
        User created = userSQLRepository.save(user);
        UserDto dto = new UserDto();
        dto.setId(created.getId());
        dto.setFirstName(created.getFirstName());
        dto.setLastName(created.getLastName());
        dto.setEmail(created.getEmail());
        dto.setDocumentNumber(created.getDocumentNumber());
        dto.setAddresses(created.getAddresses());
        dto.setPhone(created.getPhone());
        dto.setDateCreated(created.getDateCreated());
        dto.setRol(created.getRol());
        // UserMapper.userToDto(userSQLRepository.save(UserMapper.dtoToUser(singupRequest)));
        return dto;
    }

    @Override
    public void resetPassword(String email) {
        Optional<User> optionalUser = userSQLRepository.findFirstByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        User user = optionalUser.get();
        String newPassword = generateRandomPassword();

        // Cifrado de la nueva contraseña
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword)); // Cifrado
        userSQLRepository.save(user); // Guarda el usuario actualizado
    }

    private String generateRandomPassword() {
        int length = 10; // Longitud de la nueva contraseña
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
