package com.example.inmaculada.repositories.sql;

import com.example.inmaculada.domain.enums.UserRol;
import com.example.inmaculada.domain.models.User;
import com.example.inmaculada.repositories.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositorySql implements IUserRepository {

    private final IUserRepositorySql repository;

    public UserRepositorySql(IUserRepositorySql repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<User> findFirstByEmail(String email) {
        return repository.findFirstByEmail(email);
    }

    @Override
    public Optional<User> findFirstByRole(UserRol admin) {
        return repository.findFirstByRol(admin);
    }
}
