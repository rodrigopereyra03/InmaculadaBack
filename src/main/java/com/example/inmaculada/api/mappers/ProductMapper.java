package com.example.inmaculada.api.mappers;

import com.example.inmaculada.api.dto.ProductDto;
import com.example.inmaculada.domain.exceptions.CategoryNotFoundException;
import com.example.inmaculada.domain.models.Category;
import com.example.inmaculada.domain.models.Product;
import com.example.inmaculada.repositories.sql.ICategoryRepositorySql;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class ProductMapper {


    public static Product dtoToProduct(ProductDto dto, Category category) {
        if (category == null) {
            throw new IllegalArgumentException("El categoryId de ProductDto es nulo y no se encontró la categoría");
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setDescription(dto.getDescription());
        product.setImages(dto.getImages());
        product.setMainImage(dto.getMainImage());
        product.setCategory(category);

        return product;
    }

    public static ProductDto productToDto(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescription());
        dto.setImages(product.getImages());
        dto.setMainImage(product.getMainImage());
        dto.setCategoryId(product.getCategory().getId());
        return dto;
    }
}
