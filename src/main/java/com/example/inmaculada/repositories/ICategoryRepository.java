package com.example.inmaculada.repositories;

import com.example.inmaculada.domain.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICategoryRepository {
    Category save(Category category);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Category> findAllById(Set<Long> ids);
}
