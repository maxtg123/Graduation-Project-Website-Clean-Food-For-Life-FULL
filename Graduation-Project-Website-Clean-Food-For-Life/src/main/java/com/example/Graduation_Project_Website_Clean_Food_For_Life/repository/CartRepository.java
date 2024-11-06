package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // You can add custom query methods here if needed
    Optional<Cart> findByUserId(Long userId);
}
