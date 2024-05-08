package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
    User findByPhoneNumber(String phoneNumber);
}
