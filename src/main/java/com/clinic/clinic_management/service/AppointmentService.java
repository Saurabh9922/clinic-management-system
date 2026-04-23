package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.*;
import com.clinic.clinic_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Appointment booking and management.
 * Contains core business logic: booking, cancellation, validation.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    /**
     * Book an appointment for a patient.
     * Returns a message string for display.
     */
    public String bookAppointment(Long userId, Long doctorId, Long slotId) {
        // Fetch all required entities
        User user = userRepository.findById(userId).orElse(null);
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        TimeSlot slot = timeSlotRepository.findById(slotId).orElse(null);

        // Validation checks
        if (user == null || doctor == null || slot == null) {
            return "ERROR: Invalid data. Please try again.";
        }

        // Prevent double booking - check if slot is already taken
        if (slot.isBooked()) {
            return "ERROR: This slot is already booked. Please select another slot.";
        }

        // Mark the slot as booked
        slot.setBooked(true);
        timeSlotRepository.save(slot);

        // Create and save the appointment
        Appointment appointment = new Appointment(user, doctor, slot, "BOOKED");
        appointmentRepository.save(appointment);

        return "SUCCESS: Appointment booked with Dr. " + doctor.getName();
    }

    /**
     * Cancel an appointment.
     * Frees up the time slot again so others can book it.
     */
    public String cancelAppointment(Long appointmentId, Long userId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment == null) {
            return "ERROR: Appointment not found.";
        }

        // Security: only the owner can cancel their own appointment
        if (!appointment.getUser().getId().equals(userId)) {
            return "ERROR: You are not authorized to cancel this appointment.";
        }

        // Mark the time slot as available again
        TimeSlot slot = appointment.getTimeSlot();
        slot.setBooked(false);
        timeSlotRepository.save(slot);

        // Update appointment status to CANCELLED
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);

        return "SUCCESS: Appointment cancelled.";
    }

    /**
     * Get all appointments for a specific patient.
     */
    public List<Appointment> getPatientAppointments(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }

    /**
     * Get ALL appointments - for admin view.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
