package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

import java.math.BigDecimal;

public class CartItemDTO {
    private String productName;
    private BigDecimal price;
    private Integer quantity;
    private Integer stockQuantity;
    private int weight;
    private String imageUrl;

    public CartItemDTO(String productName, BigDecimal price, Integer quantity, Integer stockQuantity, int weight, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.stockQuantity = stockQuantity;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
