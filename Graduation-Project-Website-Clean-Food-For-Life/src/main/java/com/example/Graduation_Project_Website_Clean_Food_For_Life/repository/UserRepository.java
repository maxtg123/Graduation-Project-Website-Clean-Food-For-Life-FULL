package com.example.Graduation_Project_Website_Clean_Food_For_Life.repository;


import com.example.Graduation_Project_Website_Clean_Food_For_Life.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
