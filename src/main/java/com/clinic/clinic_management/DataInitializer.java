package com.clinic.clinic_management;

import com.clinic.clinic_management.entity.User;
import com.clinic.clinic_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * DataInitializer runs once when the app starts.
 * It creates a default admin account if one does not already exist.
 *
 * Admin credentials:
 *   Email:    admin@clinic.com
 *   Password: admin123
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        // Check if admin already exists to avoid duplicate creation
        if (userRepository.findByEmail("admin@clinic.com") == null) {
            User admin = new User("Admin", "admin@clinic.com", "admin123", "ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Default admin created: admin@clinic.com / admin123");
        } else {
            System.out.println("ℹ️  Admin account already exists.");
        }
    }
}
