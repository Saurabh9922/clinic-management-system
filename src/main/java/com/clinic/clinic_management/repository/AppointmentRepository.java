package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Appointment entity.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Get all appointments for a specific patient
    List<Appointment> findByUserId(Long userId);
}
