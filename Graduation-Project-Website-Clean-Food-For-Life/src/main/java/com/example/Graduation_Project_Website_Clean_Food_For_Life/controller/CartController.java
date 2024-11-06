package com.example.Graduation_Project_Website_Clean_Food_For_Life.controller;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.CartItemDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@RequestParam Long userId) {
        List<CartItemDTO> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }
}
