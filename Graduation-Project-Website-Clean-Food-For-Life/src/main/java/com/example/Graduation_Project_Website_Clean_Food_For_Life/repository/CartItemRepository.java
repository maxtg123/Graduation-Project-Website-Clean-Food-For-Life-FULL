package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // You can add custom query methods here if needed
    List<CartItem> findByCartId(Long cartId);
}
