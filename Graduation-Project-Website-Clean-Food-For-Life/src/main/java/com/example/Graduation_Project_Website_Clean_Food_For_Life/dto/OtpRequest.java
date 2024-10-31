package com.example.Graduation_Project_Website_Clean_Food_For_Life.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class OtpRequest {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "OTP không được để trống")
    private String otp;



    // Constructor không tham số
    public OtpRequest() {
    }

    // Constructor có tham số
    public OtpRequest(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getter cho email
    public String getEmail() {
        return email;
    }

    // Setter cho email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter cho otp
    public String getOtp() {
        return otp;
    }

    // Setter cho otp
    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "OtpRequest{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
