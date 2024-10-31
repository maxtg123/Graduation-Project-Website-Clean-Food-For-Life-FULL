package com.example.Graduation_Project_Website_Clean_Food_For_Life.service;

import com.example.Graduation_Project_Website_Clean_Food_For_Life.dto.*;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.Role;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.UserEntity;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.RoleRepository;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.repository.UserRepository;
import com.example.Graduation_Project_Website_Clean_Food_For_Life.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String generatedOtp;

    private String otpEmail;


    public void register(RegistrationRequest request) throws MessagingException {
        try {
            if (!isValidEmail(request.getEmail())) {
                throw new RuntimeException("Email không hợp lệ");
            }

            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new RuntimeException("Email đã tồn tại");
            }

            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setActive(false);

            Role defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));

            Set<Role> roles = new HashSet<>();
            roles.add(defaultRole);
            user.setRoles(roles);


            String generatedOtp = generateOtp();
            user.setOtp(generatedOtp); // Lưu mã OTP vào người dùng
            user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5)); // Th

            System.out.println("Saving user: " + user.getEmail());

            userRepository.save(user); // Lưu người dùng vào cơ sở dữ liệu

            // Log khi gửi OTP
            System.out.println("Sending OTP to: " + user.getEmail());
            emailService.sendOtpEmail(user.getEmail(), generatedOtp); //

//            // Log khi lưu thông tin người dùng thành công
//            System.out.println("Saving user: " + user.getEmail());
//
//            userRepository.save(user);
//
//
//            // Log khi gửi OTP
//            String generatedOtp = generateOtp();
//            System.out.println("Sending OTP to: " + user.getEmail());
//
//            emailService.sendOtpEmail(user.getEmail(), generatedOtp);
        } catch (Exception e) {
            // Log chi tiết ngoại lệ
            System.err.println("Error during registration: " + e.getMessage());
            throw e;
        }
    }


//
//    // Xác thực OTP
//    public void verifyOtp(OtpRequest request) {
//        if (generatedOtp == null) {
//            throw new RuntimeException("Mã OTP đã hết hạn hoặc không hợp lệ");
//        }
//
//        if (!request.getOtp().equals(generatedOtp)) {
//            throw new RuntimeException("Mã OTP không hợp lệ");
//        }
//
//        UserEntity user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
//
//        if (user.isActive()) {
//            throw new RuntimeException("Người dùng đã được kích hoạt trước đó");
//        }
//
//        user.setActive(true);
//        userRepository.save(user);
//        generatedOtp = null;
//    }


////     Xác thực OTP
//    public void verifyOtp(OtpRequest request) {
//        // Kiểm tra xem mã OTP đã được tạo hay chưa
//        if (generatedOtp == null) {
//            throw new RuntimeException("Mã OTP đã hết hạn hoặc không hợp lệ");
//        }
//
//        // So sánh mã OTP người dùng nhập với mã OTP đã tạo
//        if (!request.getOtp().equals(generatedOtp)) {
//            throw new RuntimeException("Mã OTP không hợp lệ");
//        }
//
//        // Tìm người dùng theo email
//        UserEntity user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
//
//        // Kiểm tra xem người dùng đã được kích hoạt hay chưa
//        if (user.isActive()) {
//            throw new RuntimeException("Người dùng đã được kích hoạt trước đó");
//        }
//
//        // Kích hoạt người dùng
//        user.setActive(true);
//        userRepository.save(user);
//        // Đặt lại mã OTP
//        generatedOtp = null;
//    }

//    public void verifyOtp(OtpRequest request) {
//        if (generatedOtp == null) {
//            throw new RuntimeException("Mã OTP đã hết hạn hoặc không hợp lệ");
//        }
//
//        if (!request.getOtp().equals(generatedOtp)) {
//            throw new RuntimeException("Mã OTP không hợp lệ");
//        }
//
//        UserEntity user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
//
//        if (user.isActive()) {
//            throw new RuntimeException("Người dùng đã được kích hoạt trước đó");
//        }
//
//        user.setActive(true);
//        userRepository.save(user);
//
//        generatedOtp = null;
//    }


    public void verifyOtp(OtpRequest request) {
        // Kiểm tra mã OTP có được cung cấp hay không
        if (request.getOtp() == null || request.getOtp().isEmpty()) {
            throw new RuntimeException("Mã OTP không được để trống");
        }

        // Tìm người dùng theo email (giả sử bạn đã lưu email trong request hoặc bạn có một cơ chế để lấy nó)
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        // Kiểm tra xem mã OTP đã được tạo hay chưa
        if (user.getOtp() == null || user.getOtpExpiryTime() == null) {
            throw new RuntimeException("Mã OTP đã hết hạn hoặc không hợp lệ");
        }

        // Kiểm tra thời gian hết hạn của mã OTP
        if (user.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Mã OTP đã hết hạn");
        }

        // So sánh mã OTP người dùng nhập với mã OTP đã lưu trong cơ sở dữ liệu
        if (!request.getOtp().equals(user.getOtp())) {
            throw new RuntimeException("Mã OTP không hợp lệ");
        }

        // Nếu mã OTP đúng, kích hoạt người dùng
        if (user.isActive()) {
            throw new RuntimeException("Người dùng đã được kích hoạt trước đó");
        }

        // Kích hoạt người dùng
        user.setActive(true);
        userRepository.save(user);

        // Đặt lại mã OTP và thời gian hết hạn
        user.setOtp(null); // Đặt lại mã OTP
        user.setOtpExpiryTime(null); // Đặt lại thời gian hết hạn
    }



//    public JwtResponse verifyOtp(OtpRequest request) {
//        // Tìm người dùng bằng email
//        UserEntity user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại")); // Kiểm tra nếu không tìm thấy người dùng
//
//        // Kiểm tra OTP
//        if (!user.getOtp().equals(request.getOtp())) {
//            throw new RuntimeException("OTP không hợp lệ"); // Nếu OTP không khớp, ném ngoại lệ
//        }
//
//        // Nếu OTP hợp lệ, tạo JWT token cho người dùng
//        String token = jwtUtils.generateToken(user); // Giả định bạn có một phương thức để tạo token
//
//        // Trả về token và thông tin người dùng trong đối tượng JwtResponse
//        return new JwtResponse(token, user.getId(), user.getUsername());
//    }



//    // Đăng nhập và tạo JWT
//    public JwtResponse login(LoginRequest request) {
//        UserEntity user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Mật khẩu không chính xác");
//        }
//
//        String token = jwtUtils.generateToken(user.getEmail());
//        return new JwtResponse(token);
//    }

    public JwtResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu không chính xác");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        // Trả về JwtResponse với token và id của người dùng
        return new JwtResponse(token, user.getId(),user.getUsername()); // Truyền userId
    }

    // Kiểm tra tính hợp lệ của email
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    // Tạo mã OTP ngẫu nhiên
    private String generateOtp() {
        Random rand = new Random();
        int otp = rand.nextInt(999999);
        return String.format("%06d", otp);
    }

    // Lấy thông tin người dùng theo ID
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Cập nhật thông tin người dùng
    public UserEntity updateUser(Long userId, UserUpdateRequest request) {
        UserEntity user = getUserById(userId);

        if (request.getEmail() != null && !request.getEmail().isEmpty()) {
            Optional<UserEntity> userWithSameEmail = userRepository.findByEmail(request.getEmail());
            if (userWithSameEmail.isPresent() && !userWithSameEmail.get().getId().equals(user.getId())) {
                throw new RuntimeException("Email đã tồn tại.");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getUsername() != null && !request.getUsername().isEmpty()) {
            user.setUsername(request.getUsername());
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            user.setPhone(request.getPhone());
        }

        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            user.setAddress(request.getAddress());
        }

        return userRepository.save(user);
    }

//     Cập nhật hình ảnh đại diện
    public UserEntity updateUserAvatar(Long userId, MultipartFile profileImage) throws IOException {
        UserEntity user = getUserById(userId);

        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
            Path filePath = Paths.get("uploads/" + fileName);
            Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setProfileImage("http://localhost:8080/uploads/" + fileName);
        }

        return userRepository.save(user);
    }

//
//    public UserEntity updateUserAvatar(Long userId, MultipartFile profileImage) throws IOException {
//        UserEntity user = getUserById(userId);
//
//        if (user == null) {
//            throw new RuntimeException("User not found with ID: " + userId); // Xử lý trường hợp không tìm thấy người dùng
//        }
//
//        if (profileImage != null && !profileImage.isEmpty()) {
//            // Tạo tên file ngẫu nhiên và lưu tệp
//            String fileName = UUID.randomUUID().toString() + "_" + profileImage.getOriginalFilename();
//            Path filePath = Paths.get("uploads/" + fileName);
//
//            try {
//                Files.copy(profileImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//                user.setProfileImage("http://localhost:8080/uploads/" + fileName); // Cập nhật đường dẫn hình ảnh
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to save profile image: " + e.getMessage());
//            }
//        }
//
//        return userRepository.save(user); // Lưu lại thông tin người dùng đã cập nhật vào cơ sở dữ liệu
//    }

}
