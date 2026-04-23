package com.clinic.clinic_management.controller;

import com.clinic.clinic_management.entity.*;
import com.clinic.clinic_management.service.AppointmentService;
import com.clinic.clinic_management.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PatientController - handles all patient actions:
 * view dashboard, browse doctors, book & cancel appointments, view my appointments.
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    // ==============================
    // PATIENT DASHBOARD
    // ==============================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        // Get logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        // If not logged in, redirect to login page
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "patient/dashboard"; // -> templates/patient/dashboard.html
    }

    // ==============================
    // VIEW ALL DOCTORS (Step 1)
    // ==============================
    @GetMapping("/doctors")
    public String viewDoctors(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("user", user);

        return "patient/doctors"; // -> templates/patient/doctors.html
    }

    // ==============================
    // VIEW AVAILABLE SLOTS FOR A DOCTOR (Step 2)
    // ==============================
    @GetMapping("/slots/{doctorId}")
    public String viewSlots(@PathVariable Long doctorId,
                            HttpSession session,
                            Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) {
            return "redirect:/patient/doctors";
        }

        // Only show available (not booked) slots
        List<TimeSlot> availableSlots = doctorService.getAvailableSlots(doctorId);

        model.addAttribute("doctor", doctor);
        model.addAttribute("slots", availableSlots);
        model.addAttribute("user", user);

        return "patient/slots"; // -> templates/patient/slots.html
    }

    // ==============================
    // BOOK APPOINTMENT (Step 3)
    // ==============================
    @PostMapping("/book")
    public String bookAppointment(@RequestParam Long doctorId,
                                  @RequestParam Long slotId,
                                  HttpSession session,
                                  Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        // Service handles all booking logic and double-booking prevention
        String result = appointmentService.bookAppointment(user.getId(), doctorId, slotId);

        // Redirect to My Appointments page after booking
        return "redirect:/patient/my-appointments?msg=" + result;
    }

    // ==============================
    // VIEW MY APPOINTMENTS
    // ==============================
    @GetMapping("/my-appointments")
    public String myAppointments(HttpSession session,
                                 Model model,
                                 @RequestParam(required = false) String msg) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Appointment> appointments = appointmentService.getPatientAppointments(user.getId());

        model.addAttribute("appointments", appointments);
        model.addAttribute("user", user);
        model.addAttribute("msg", msg); // Show success/error message if any

        return "patient/my-appointments"; // -> templates/patient/my-appointments.html
    }

    // ==============================
    // CANCEL APPOINTMENT
    // ==============================
    @PostMapping("/cancel/{appointmentId}")
    public String cancelAppointment(@PathVariable Long appointmentId,
                                    HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        appointmentService.cancelAppointment(appointmentId, user.getId());

        return "redirect:/patient/my-appointments";
    }
}
