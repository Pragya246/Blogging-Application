package com.study.blogappapis.controller;

import com.study.blogappapis.payloads.ApiResponse;
import com.study.blogappapis.payloads.CategoryDto;
import com.study.blogappapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

//    add-category
    @PostMapping("/add-category")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory=categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }
//    update-category
    @PutMapping("/update-category/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return ResponseEntity.ok(updatedCategory);
    }
//    delete-category
    @DeleteMapping("/delete-category/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse("category deleted successfully",true));
    }
//    get-categorybyid
    @GetMapping("get-categoryById/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        CategoryDto categoryDto=categoryService.getCategory(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.FOUND);
    }
//    get-allcategory
    @GetMapping("get-allCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDtos=categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }
}
