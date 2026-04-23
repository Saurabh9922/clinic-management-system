package com.clinic.clinic_management.service;

import com.clinic.clinic_management.entity.Doctor;
import com.clinic.clinic_management.entity.TimeSlot;
import com.clinic.clinic_management.repository.DoctorRepository;
import com.clinic.clinic_management.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Doctor and TimeSlot management.
 * Used mainly by Admin features.
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    // ---- Doctor Operations ----

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // ---- TimeSlot Operations ----

    /**
     * Add a time slot to a specific doctor.
     * Returns null if doctor not found.
     */
    public TimeSlot addTimeSlot(Long doctorId, TimeSlot slot) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        if (doctor == null) {
            return null;
        }
        slot.setDoctor(doctor);
        return timeSlotRepository.save(slot);
    }

    /**
     * Get available (not yet booked) time slots for a doctor.
     */
    public List<TimeSlot> getAvailableSlots(Long doctorId) {
        return timeSlotRepository.findByDoctorIdAndIsBookedFalse(doctorId);
    }

    /**
     * Get ALL time slots for a doctor (for admin view).
     */
    public List<TimeSlot> getAllSlots(Long doctorId) {
        return timeSlotRepository.findByDoctorId(doctorId);
    }

    public void deleteSlot(Long slotId) {
        timeSlotRepository.deleteById(slotId);
    }
}
