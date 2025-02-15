package com.example.inmaculada.repositories.sql;

import com.example.inmaculada.domain.enums.UserRol;
import com.example.inmaculada.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepositorySql extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);

    User findByRol(UserRol admin);

    Optional<User> findFirstByRol(UserRol rol);
}
