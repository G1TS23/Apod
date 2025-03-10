package com.apod.repository;


import com.apod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    User findByClaimToken(String token);
}
