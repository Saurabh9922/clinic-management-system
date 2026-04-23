package com.clinic.clinic_management.repository;

import com.clinic.clinic_management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Doctor entity.
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // findAll(), findById(), save(), deleteById() - all come from JpaRepository
}
