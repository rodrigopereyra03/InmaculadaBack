package com.example.inmaculada.repositories.sql;

import com.example.inmaculada.domain.models.Category;
import com.example.inmaculada.repositories.ICategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositorySql implements ICategoryRepository {

    private final ICategoryRepositorySql repository;

    public CategoryRepositorySql(ICategoryRepositorySql repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category category) {
        return repository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
