package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Thêm các phương thức truy vấn tuỳ chỉnh tại đây nếu cần
}