package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.UserDto;

import java.util.List;

public interface CategoryService {
    public void createCategory(CategoryDto categoryDto);
    public CategoryDto getCategory(Integer id);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public int deleteCategory(Integer id);
    List<CategoryDto> getAllCategories();
}
