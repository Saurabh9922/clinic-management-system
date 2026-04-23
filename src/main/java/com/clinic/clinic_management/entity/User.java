package com.clinic.clinic_management.entity;

import jakarta.persistence.*;

/**
 * User entity - represents both patients and admins.
 * Role is stored as a string: "PATIENT" or "ADMIN"
 */
@Entity
@Table(name = "users") // "user" is a reserved keyword in MySQL, so we use "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // Role can be: PATIENT or ADMIN
    private String role;

    // Default constructor required by JPA
    public User() {}

    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
