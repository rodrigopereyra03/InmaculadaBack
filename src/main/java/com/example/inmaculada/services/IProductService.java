package com.example.inmaculada.services;

import com.example.inmaculada.api.dto.ProductDto;

import java.util.List;

public interface IProductService {

    ProductDto createProduct(ProductDto productDto);

    List<ProductDto> getAllProducts(Long categoryId);

    ProductDto getProductById(Long id);

    ProductDto updateProduct(ProductDto productDto);

    String deleteProduct(Long id);

    List<ProductDto> findProductsByName(String name);

    List<ProductDto> findProductsByPrinceRange(Double minPrice, Double maxPrice);
}
