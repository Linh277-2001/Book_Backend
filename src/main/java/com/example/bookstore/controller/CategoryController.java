package com.example.bookstore.controller;

import com.example.bookstore.model.Category;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping("/all")
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    @PostMapping
    public Category insertCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @PutMapping("{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category){
        Category updateCategory=categoryRepository.findById(id).get();
        updateCategory.setName(category.getName());
        return categoryRepository.save(updateCategory);
    }
    @GetMapping("/count")
    public long countAllCategories(){
        return categoryRepository.count();
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        try{
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("OK");
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
