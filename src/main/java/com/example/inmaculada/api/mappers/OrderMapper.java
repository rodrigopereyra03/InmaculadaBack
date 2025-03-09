package com.example.inmaculada.api.mappers;


import com.example.inmaculada.api.dto.OrderDto;
import com.example.inmaculada.api.dto.ProductDto;
import com.example.inmaculada.domain.models.Category;
import com.example.inmaculada.domain.models.Order;
import com.example.inmaculada.domain.models.OrderProduct;
import com.example.inmaculada.domain.models.Product;
import com.example.inmaculada.repositories.ICategoryRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    public OrderDto toOrderDTO(Order order) {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setId(order.getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setAddress(order.getAddress());

        List<ProductDto> productDtos = order.getOrderProducts().stream()
                .map(orderProduct -> {
                    Product product = orderProduct.getProduct();
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setDescription(product.getDescription());
                    productDto.setPrice(product.getPrice());
                    productDto.setMainImage(product.getMainImage());
                    productDto.setImages(product.getImages());
                    productDto.setQuantity(orderProduct.getQuantity());
                    productDto.setCategoryId(product.getCategory().getId());
                    return productDto;
                })
                .collect(Collectors.toList());
        orderDTO.setProducts(productDtos);

        orderDTO.setStatus(order.getStatus());
        orderDTO.setDateCreated(order.getDateCreated());
       // orderDTO.setUser(order.getUser());
        orderDTO.setComprobanteUrl(order.getComprobanteUrl());
        orderDTO.setPickup(order.getPickup());
        return orderDTO;
    }

    public Order toOrder(OrderDto orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setAmount(orderDTO.getAmount());
        order.setAddress(orderDTO.getAddress());

        Set<Long> categoryIds = orderDTO.getProducts().stream()
                .map(ProductDto::getCategoryId)
                .filter(Objects::nonNull)  // Filtramos los IDs nulos para evitar buscar categorías con ID nulo
                .collect(Collectors.toSet());

        if (!categoryIds.isEmpty()) {
            Map<Long, Category> categoryMap = iCategoryRepository.findAllById(categoryIds).stream()
                    .collect(Collectors.toMap(Category::getId, category -> category));

            order.setOrderProducts(orderDTO.getProducts().stream()
                    .map(productDto -> {
                        Category category = categoryMap.get(productDto.getCategoryId());
                        if (category == null) {
                            throw new RuntimeException("Categoría no encontrada con ID: " + productDto.getCategoryId());
                        }
                        Product product = ProductMapper.dtoToProduct(productDto, category);
                        OrderProduct orderProduct = new OrderProduct();
                        orderProduct.setProduct(product);
                        return orderProduct;
                    })
                    .collect(Collectors.toList()));
        }
        order.setStatus(orderDTO.getStatus());
        order.setDateCreated(LocalDateTime.now());
        order.setUser(orderDTO.getUser());
        order.setPickup(orderDTO.getPickup());
        return order;
    }
}
