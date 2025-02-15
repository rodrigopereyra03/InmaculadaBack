package com.example.inmaculada.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String mainImage;
    private List<ProductDto> productDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<ProductDto> getProductDto() {
        return productDto;
    }

    public void setProductDto(List<ProductDto> productDto) {
        this.productDto = productDto;
    }
}
