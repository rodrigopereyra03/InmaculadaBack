package com.example.inmaculada.api.controllers;

import com.example.inmaculada.api.dto.CategoryDto;
import com.example.inmaculada.services.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {
    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto dto){
        CategoryDto createdCategory = iCategoryService.createCategory(dto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = iCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto category = iCategoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/category")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.OK).body(iCategoryService.updateCategory(categoryDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        String result = iCategoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
