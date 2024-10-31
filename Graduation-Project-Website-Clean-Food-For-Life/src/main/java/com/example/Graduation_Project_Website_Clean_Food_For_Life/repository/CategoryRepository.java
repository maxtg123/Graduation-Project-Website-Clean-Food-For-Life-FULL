package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Tìm kiếm danh mục theo tên (nếu cần)
    Optional<Category> findByName(String name);
}
