package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

public class JwtResponse {
    private String token;
    private Long id; // Trường id
    private String username; // Trường username

    // Constructor
    public JwtResponse(String token, Long id, String username) { // Thêm tham số username vào constructor
        this.token = token;
        this.id = id; // Khởi tạo id
        this.username = username; // Khởi tạo username
    }

    // Getter và Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id; // Getter cho id
    }

    public void setId(Long id) {
        this.id = id; // Setter cho id
    }

    public String getUsername() {
        return username; // Getter cho username
    }

    public void setUsername(String username) {
        this.username = username; // Setter cho username
    }
}
