package com.example.Graduation_Project_Website_Clean_Food_For_Life.controller;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.UserUpdateRequest;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.UserEntity;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        try {
            UserEntity user = authService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUserProfile(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest userUpdateRequest) {

        try {
            // Cập nhật thông tin người dùng
            UserEntity updatedUser = authService.updateUser(id, userUpdateRequest);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


    @PostMapping("/{id}/avatar")
    public ResponseEntity<UserEntity> updateUserAvatar(
            @PathVariable Long id,
            @RequestParam("profileImage") MultipartFile profileImage) {

        try {
            // Lấy user từ authService
            UserEntity user = authService.getUserById(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Tạo thư mục uploads nếu chưa tồn tại
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);  // Tạo thư mục nếu chưa tồn tại
            }

            // Xử lý việc lưu hình ảnh
            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);  // Đường dẫn đầy đủ đến file
            Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Cập nhật URL hình ảnh vào user
            user.setProfileImage("http://localhost:8080/uploads/" + fileName);

            // Cập nhật thông tin vào cơ sở dữ liệu
            UserEntity updatedUser = authService.updateUserAvatar(id, profileImage);

            return ResponseEntity.ok(updatedUser);
        } catch (IOException e) {
            e.printStackTrace();  // In lỗi ra console để dễ debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Trả về null thay vì String
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Trả về null thay vì String
        }
    }



}
