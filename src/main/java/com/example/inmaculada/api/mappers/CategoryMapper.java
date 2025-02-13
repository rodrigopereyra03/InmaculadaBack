package com.example.inmaculada.api.mappers;

import com.example.inmaculada.api.dto.CategoryDto;
import com.example.inmaculada.domain.models.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category dtoToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setMainImage(categoryDto.getMainImage());
        //category.setProducts(categoryDto.getProductDto());
        return category;
    }

    public static CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setMainImage(category.getMainImage());
        //categoryDto.setProductDto(category.getProducts());
        return categoryDto;
    }
}
