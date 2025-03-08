package com.example.inmaculada.repositories;

import com.example.inmaculada.domain.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    boolean existsById(Long id);

    List<Product> findByCategoryId(Long categoryId);
}
