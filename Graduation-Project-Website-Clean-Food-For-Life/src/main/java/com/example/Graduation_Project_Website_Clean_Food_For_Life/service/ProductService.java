package com.example.Graduation_Project_Website_Clean_Food_For_Life.service;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Product;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
