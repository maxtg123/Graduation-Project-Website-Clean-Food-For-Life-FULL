package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

public class RegistrationRequest {
    private String username;
    private String email;
    private String password;

    // Constructor không tham số
    public RegistrationRequest() {
    }

    // Constructor có tham số
    public RegistrationRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter cho username
    public String getUsername() {
        return username;
    }

    // Setter cho username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter cho email
    public String getEmail() {
        return email;
    }

    // Setter cho email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter cho password
    public String getPassword() {
        return password;
    }

    // Setter cho password
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
