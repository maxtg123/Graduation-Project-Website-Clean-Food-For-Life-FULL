package com.example.Graduation_Project_Website_Clean_Food_For_Life.service;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.CartItemDTO;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Cart;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.CartItem;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.CartItemRepository;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.CartRepository;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItemDTO> getCartItems(Long userId) {
        // Lấy giỏ hàng của người dùng
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Giỏ hàng không tồn tại"));

        // Lấy danh sách các sản phẩm trong giỏ hàng và chuyển đổi thành CartItemDTO
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        return cartItems.stream().map(cartItem -> new CartItemDTO(
                cartItem.getProductVariant().getProduct().getName(), // Tên sản phẩm
                cartItem.getPrice(), // Giá sản phẩm
                cartItem.getQuantity(), // Số lượng
                cartItem.getProductVariant().getStockQuantity(), // Số lượng tồn kho
                cartItem.getProductVariant().getWeight(), // Cân nặng
                cartItem.getProductVariant().getImageUrl() // Đường dẫn hình ảnh
        )).collect(Collectors.toList());
    }
}
