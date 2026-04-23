package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for TimeSlot entity.
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    // Find all available (not booked) slots for a given doctor
    // Spring Data JPA reads method name and generates:
    // SELECT * FROM time_slot WHERE doctor_id = ? AND is_booked = false
    List<TimeSlot> findByDoctorIdAndIsBookedFalse(Long doctorId);

    // Find ALL slots (booked + available) for a doctor - used by admin
    List<TimeSlot> findByDoctorId(Long doctorId);
}
