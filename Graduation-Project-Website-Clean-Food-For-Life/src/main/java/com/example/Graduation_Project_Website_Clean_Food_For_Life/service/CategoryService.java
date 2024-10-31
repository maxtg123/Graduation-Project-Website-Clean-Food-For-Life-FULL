package com.example.Graduation_Project_Website_Clean_Food_For_Life.service;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Category;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}
