package com.example.inmaculada.api.mappers;

import com.example.inmaculada.api.dto.CategoryDto;
import com.example.inmaculada.api.dto.ProductDto;
import com.example.inmaculada.domain.models.Category;
import com.example.inmaculada.domain.models.Product;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CategoryMapper {

    public static Category dtoToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setMainImage(categoryDto.getMainImage());
        return category;
    }

    public static CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setMainImage(category.getMainImage());
        // Mapear la lista de productos de Category a CategoryDto
        List<ProductDto> productDtos = category.getProducts() != null
                ? category.getProducts().stream()
                .map(ProductMapper::productToDto)
                .collect(Collectors.toList())
                :Collections.emptyList();
        categoryDto.setProductDto(productDtos);
        return categoryDto;
    }
}
