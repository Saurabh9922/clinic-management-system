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
 * AdminController - handles all admin actions:
 * manage doctors, manage time slots, view all appointments.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    // Helper method: check if the logged-in user is admin
    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equals(user.getRole());
    }

    // ==============================
    // ADMIN DASHBOARD
    // ==============================
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Appointment> appointments = appointmentService.getAllAppointments();

        model.addAttribute("doctors", doctors);
        model.addAttribute("appointments", appointments);
        model.addAttribute("doctorCount", doctors.size());
        model.addAttribute("appointmentCount", appointments.size());

        return "admin/dashboard"; // -> templates/admin/dashboard.html
    }

    // ==============================
    // VIEW ALL DOCTORS
    // ==============================
    @GetMapping("/doctors")
    public String viewDoctors(HttpSession session, Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("newDoctor", new Doctor()); // For the add-doctor form

        return "admin/doctors"; // -> templates/admin/doctors.html
    }

    // ==============================
    // ADD DOCTOR (form submit)
    // ==============================
    @PostMapping("/doctors/add")
    public String addDoctor(@ModelAttribute Doctor doctor,
                            HttpSession session) {

        if (!isAdmin(session)) return "redirect:/login";

        doctorService.addDoctor(doctor);
        return "redirect:/admin/doctors?success=Doctor added successfully";
    }

    // ==============================
    // DELETE DOCTOR
    // ==============================
    @PostMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id,
                               HttpSession session) {

        if (!isAdmin(session)) return "redirect:/login";

        doctorService.deleteDoctor(id);
        return "redirect:/admin/doctors";
    }

    // ==============================
    // VIEW SLOTS FOR A DOCTOR
    // ==============================
    @GetMapping("/slots/{doctorId}")
    public String viewSlots(@PathVariable Long doctorId,
                            HttpSession session,
                            Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor == null) return "redirect:/admin/doctors";

        List<TimeSlot> slots = doctorService.getAllSlots(doctorId);

        model.addAttribute("doctor", doctor);
        model.addAttribute("slots", slots);
        model.addAttribute("newSlot", new TimeSlot()); // For the add-slot form

        return "admin/slots"; // -> templates/admin/slots.html
    }

    // ==============================
    // ADD TIME SLOT
    // ==============================
    @PostMapping("/slots/add/{doctorId}")
    public String addSlot(@PathVariable Long doctorId,
                          @ModelAttribute TimeSlot slot,
                          HttpSession session) {

        if (!isAdmin(session)) return "redirect:/login";

        doctorService.addTimeSlot(doctorId, slot);
        return "redirect:/admin/slots/" + doctorId;
    }

    // ==============================
    // DELETE TIME SLOT
    // ==============================
    @PostMapping("/slots/delete/{slotId}/{doctorId}")
    public String deleteSlot(@PathVariable Long slotId,
                             @PathVariable Long doctorId,
                             HttpSession session) {

        if (!isAdmin(session)) return "redirect:/login";

        doctorService.deleteSlot(slotId);
        return "redirect:/admin/slots/" + doctorId;
    }

    // ==============================
    // VIEW ALL APPOINTMENTS
    // ==============================
    @GetMapping("/appointments")
    public String viewAllAppointments(HttpSession session, Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "admin/appointments"; // -> templates/admin/appointments.html
    }
}
