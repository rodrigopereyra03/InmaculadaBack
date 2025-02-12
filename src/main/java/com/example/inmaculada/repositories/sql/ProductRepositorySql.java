package com.example.inmaculada.repositories.sql;

import com.example.inmaculada.domain.models.Product;
import com.example.inmaculada.repositories.IProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductRepositorySql implements IProductRepository {

    private final IProductRepositorySql repository;

    public ProductRepositorySql(IProductRepositorySql repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> findByPriceBetween(Double minPrice, Double maxPrice) {
        return repository.findByPriceBetween(minPrice,maxPrice);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
