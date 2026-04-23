package com.clinic.clinic_management.controller;

import com.clinic.clinic_management.entity.User;
import com.clinic.clinic_management.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController - handles login and registration for both Patient and Admin.
 * Uses HTTP session to store logged-in user info.
 */
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ==============================
    // SHOW LOGIN PAGE
    // ==============================
    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // -> templates/login.html
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // ==============================
    // PROCESS LOGIN FORM
    // ==============================
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        // Validate credentials using service layer
        User user = userService.login(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";
        }

        // Store user in session so we can access it across pages
        session.setAttribute("loggedInUser", user);

        // Redirect based on role
        if ("ADMIN".equals(user.getRole())) {
            return "redirect:/admin/dashboard";
        } else {
            return "redirect:/patient/dashboard";
        }
    }

    // ==============================
    // SHOW REGISTER PAGE
    // ==============================
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User()); // Empty user object for form binding
        return "register"; // -> templates/register.html
    }

    // ==============================
    // PROCESS REGISTER FORM
    // ==============================
    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user, Model model) {

        // Basic validation
        if (user.getName() == null || user.getName().isEmpty()) {
            model.addAttribute("error", "Name is required.");
            return "register";
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            model.addAttribute("error", "Email is required.");
            return "register";
        }
        if (user.getPassword() == null || user.getPassword().length() < 4) {
            model.addAttribute("error", "Password must be at least 4 characters.");
            return "register";
        }

        // Try to register - returns null if email already taken
        User registered = userService.register(user);

        if (registered == null) {
            model.addAttribute("error", "Email is already registered. Please login.");
            return "register";
        }

        // Registration successful - go to login
        return "redirect:/login?registered=true";
    }

    // ==============================
    // LOGOUT
    // ==============================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear all session data
        return "redirect:/login?logout=true";
    }
}
