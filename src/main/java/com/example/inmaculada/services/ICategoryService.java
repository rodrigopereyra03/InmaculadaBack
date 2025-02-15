package com.example.inmaculada.services;

import com.example.inmaculada.api.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategory(CategoryDto categoryDto);

    String deleteCategory(Long id);
}
