package com.clinic.clinic_management.entity;

import jakarta.persistence.*;

/**
 * TimeSlot entity.
 * Each slot belongs to one doctor and can be booked once.
 */
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startTime; // e.g. "09:00 AM"
    private String endTime;   // e.g. "09:30 AM"

    // false = available, true = already booked
    private boolean isBooked = false;

    // Many slots -> One doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public TimeSlot() {}

    public TimeSlot(String startTime, String endTime, Doctor doctor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctor = doctor;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
