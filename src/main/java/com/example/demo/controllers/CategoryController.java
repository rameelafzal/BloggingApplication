package com.example.demo.controllers;

import com.example.demo.payloads.CategoryDto;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        this.categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")

    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id) {
        CategoryDto categoryDto=this.categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
    }
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDto=this.categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }
    @DeleteMapping ("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestBody Integer id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.CREATED);
    }
}
