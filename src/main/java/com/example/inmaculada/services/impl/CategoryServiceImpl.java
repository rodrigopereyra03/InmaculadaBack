package com.example.inmaculada.services.impl;

import com.example.inmaculada.api.dto.CategoryDto;
import com.example.inmaculada.api.mappers.CategoryMapper;
import com.example.inmaculada.domain.exceptions.CategoryNotFoundException;
import com.example.inmaculada.domain.models.Category;
import com.example.inmaculada.repositories.ICategoryRepository;
import com.example.inmaculada.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository repository;

    public CategoryServiceImpl(ICategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.dtoToCategory(categoryDto);
        return CategoryMapper.categoryToDto(repository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = repository.findAll();
        return categories.stream()
                .map(CategoryMapper::categoryToDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return CategoryMapper.categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Optional<Category> category = repository.findById(categoryDto.getId());

        if (category.isPresent()) {
            Category entity = category.get();

            if (categoryDto.getName() != null) {
                entity.setName(categoryDto.getName());
            }
            if (categoryDto.getMainImage()!= null){
                entity.setMainImage(categoryDto.getMainImage());
            }

            Category saved = repository.save(entity);
            return CategoryMapper.categoryToDto(saved);
        } else {
            throw new CategoryNotFoundException("Category not found with id: " + categoryDto.getId());
        }
    }

    @Override
    public String deleteCategory(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "The category has been successfully deleted";
        } else {
            return "Category not found with id: " + id;
        }
    }

}
