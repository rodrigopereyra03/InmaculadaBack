package com.example.inmaculada.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String mainImage;
    private List<ProductDto> productDto;
}
