package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

public class UserUpdateRequest {

    private String username; // Tên người dùng

    private String email; // Không bắt buộc

    private String phone; // Không bắt buộc

    private String address; // Không bắt buộc

    private String profileImage; // Không bắt buộc

    // Getters và setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
