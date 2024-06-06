package com.baeldung.crud.service;

import com.baeldung.crud.entities.Category;
import com.baeldung.crud.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public List<Category> saveCategories(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElse(null);
    }
    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

//    public void addCategory(Category category) {
//    }

    public String deleteCategory(int id){
        categoryRepository.deleteById(id);
        return "category removed";
    }

    public Optional<Category> updateCategory(int id){
        return categoryRepository.findById(id);
    }
}
