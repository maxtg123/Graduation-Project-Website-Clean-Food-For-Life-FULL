package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

import java.math.BigDecimal;

public class ProductDetailDTO {
    private Long id;
    private String name;
    private String description;
    private double rating;
    private int reviewsCount;
    private BigDecimal price;
    private boolean availability;
    private String additionalInfo;
    private String imageUrl;           // Thêm trường imageUrl
    private String details;             // Thêm trường details
    private String specifications;      // Thêm trường specifications
    private String shippingInfo;        // Thêm trường shippingInfo
    private double weight;              // Thêm trường weight

    // Constructor
    public ProductDetailDTO(Long id, String name, String description, double rating,
                            int reviewsCount, BigDecimal price, boolean availability,
                            String additionalInfo, String imageUrl, String details,
                            String specifications, String shippingInfo, double weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
        this.price = price;
        this.availability = availability;
        this.additionalInfo = additionalInfo;
        this.imageUrl = imageUrl;
        this.details = details;
        this.specifications = specifications;
        this.shippingInfo = shippingInfo;
        this.weight = weight;
    }

    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
