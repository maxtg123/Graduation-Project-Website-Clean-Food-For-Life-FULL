package com.example.Graduation_Project_Website_Clean_Food_For_Life.service;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.Exception.ResourceNotFoundException;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.ProductDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.ProductDetailDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Product;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }



}




