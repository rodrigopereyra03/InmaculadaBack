package com.example.inmaculada.repositories.sql;

import com.example.inmaculada.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepositorySql extends JpaRepository<Category, Long> {
}
