package com.example.demo.services.impl;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repositories.CategoryRepo;

import com.example.demo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category=this.toCategory(categoryDto);
        Category savedCategory= this.categoryRepo.save(category);
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        Category category=this.categoryRepo.findById(id).orElseThrow((()->new ResourceNotFoundException("User","User Id",3)));
        return this.toCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category=this.toCategory(categoryDto);
        Category category1=this.categoryRepo.findById(categoryDto.getCategoryId()).orElseThrow((()->new ResourceNotFoundException("User","User Id",3)));
        Category savedCategory= this.categoryRepo.save(category);
        return this.toCategoryDto(savedCategory);
    }

    @Override
    public int deleteCategory(Integer id) {
        Category category=this.categoryRepo.findById(id).orElseThrow((()->new ResourceNotFoundException("User","User Id",id)));
        this.categoryRepo.delete(category);
        return 0;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map(category -> this.toCategoryDto(category)).collect(Collectors.toList());
        return categoryDtoList;
    }

    private Category toCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto, Category.class);
    }
    private CategoryDto toCategoryDto(Category category){
        return this.modelMapper.map(category, CategoryDto.class);
    }
}
