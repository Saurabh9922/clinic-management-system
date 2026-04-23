package com.clinic.clinic_management.config;

import com.clinic.clinic_management.entity.*;
import com.clinic.clinic_management.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public void run(String... args) {

        System.out.println("🔥 DataLoader running...");

        // ==========================
        // ADD DOCTORS
        // ==========================
        if (doctorRepository.count() == 0) {

            Doctor d1 = doctorRepository.save(new Doctor("Dr. Amit Sharma", "Cardiologist"));
            Doctor d2 = doctorRepository.save(new Doctor("Dr. Priya Patil", "Dentist"));
            Doctor d3 = doctorRepository.save(new Doctor("Dr. Rahul Deshmukh", "Orthopedic"));
            Doctor d4 = doctorRepository.save(new Doctor("Dr. Sneha Kulkarni", "Dermatologist"));

            System.out.println("✅ Doctors added");

            // ==========================
            // ADD TIME SLOTS
            // ==========================

            addSlots(d1);
            addSlots(d2);
            addSlots(d3);
            addSlots(d4);

            System.out.println("✅ Time slots added");
        }
    }

    // Helper method
    private void addSlots(Doctor doctor) {

        timeSlotRepository.save(new TimeSlot("10:00", "10:30", doctor));
        timeSlotRepository.save(new TimeSlot("10:30", "11:00", doctor));
        timeSlotRepository.save(new TimeSlot("11:00", "11:30", doctor));
    }
}