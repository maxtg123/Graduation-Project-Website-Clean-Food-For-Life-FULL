package com.example.Graduation_Project_Website_Clean_Food_For_Life.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/register", "/api/auth/activate", "/api/auth/login","/api/profile/**","/api/profile/**/avatar","/api/products/","/api/products/**" ).permitAll() // Các đường dẫn công khai
//                        .requestMatchers("/api/profile/**").authenticated() // Bảo vệ endpoint cập nhật thông tin
//                        .requestMatchers("/api/products/**","/api/products").permitAll()
//                        .anyRequest().authenticated() // Các yêu cầu khác cần xác thực
//                )
//                .csrf(csrf -> csrf.disable()); // Tắt CSRF nếu bạn đang sử dụng API

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/activate", "/api/auth/login").permitAll() // Các đường dẫn công khai
                        .requestMatchers("/api/profile/**").authenticated() // Bảo vệ endpoint cập nhật thông tin
//                        .requestMatchers("/api/products","/api/products/**","/api/products/image/**").permitAll()
                                .requestMatchers("/api/products", "/api/products/**", "/api/products/image/**").permitAll()

                                .anyRequest().authenticated() // Các yêu cầu khác cần xác thực
                )
                .csrf(csrf -> csrf.disable()); // Tắt CSRF nếu bạn đang sử dụng API

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
