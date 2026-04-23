package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for User entity.
 * JpaRepository gives us save(), findById(), findAll(), deleteById() for free.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA auto-generates SQL: SELECT * FROM users WHERE email = ?
    User findByEmail(String email);

    // Check if email already exists (for registration validation)
    boolean existsByEmail(String email);
}
