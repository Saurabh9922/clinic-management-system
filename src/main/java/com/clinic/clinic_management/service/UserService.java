package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.User;
import com.clinic.clinic_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for User-related business logic.
 * Contains registration and login validation.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new patient.
     * Returns null if email already taken, otherwise saves and returns user.
     */
    public User register(User user) {
        // Check if email is already registered
        if (userRepository.existsByEmail(user.getEmail())) {
            return null; // Email already taken
        }
        // Set role as PATIENT for self-registration
        user.setRole("PATIENT");
        return userRepository.save(user);
    }

    /**
     * Validate login credentials.
     * Returns the User if credentials match, otherwise null.
     */
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        // Check if user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // Invalid credentials
    }
}
