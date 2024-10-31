 package com.example.Graduation_Project_Website_Clean_Food_For_Life.controller;
 import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.JwtResponse;
 import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.LoginRequest;
 import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.OtpRequest;
 import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.RegistrationRequest;

 import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.UserRepository;

 import com.example.Graduation_Project_Website_Clean_Food_For_Life.service.AuthService;

 import jakarta.mail.MessagingException;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


 import java.util.Collections;
 import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;




    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegistrationRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            authService.register(request);
            response.put("success", true);
            response.put("message", "Đăng ký thành công, vui lòng kiểm tra email để nhận OTP");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("error", "Lỗi đăng ký: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (MessagingException e) {
            response.put("success", false);
            response.put("error", "Lỗi gửi email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




    @PostMapping("/activate")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody OtpRequest request) {
        Map<String, String> response = new HashMap<>();
        try {
            authService.verifyOtp(request);
            response.put("message", "Xác minh OTP thành công");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }





    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Gọi phương thức login từ AuthService
            JwtResponse jwtResponse = authService.login(loginRequest);
            response.put("success", true);
            response.put("token", jwtResponse.getToken()); // Thêm token vào response
            response.put("id", jwtResponse.getId());
            response.put("username", jwtResponse.getUsername());// Thêm ID người dùng vào response
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}
