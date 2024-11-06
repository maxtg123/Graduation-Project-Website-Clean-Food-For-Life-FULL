package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    // Các phương thức tìm kiếm bổ sung nếu cần
}
