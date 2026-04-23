package com.clinic.clinic_management.entity;

import jakarta.persistence.*;

/**
 * Appointment entity.
 * Links a patient (User) to a Doctor via a TimeSlot.
 * Status: BOOKED or CANCELLED
 */
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many appointments can belong to one user (patient)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many appointments can be for one doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // One appointment uses exactly one time slot
    @OneToOne
    @JoinColumn(name = "timeslot_id")
    private TimeSlot timeSlot;

    // Status: BOOKED or CANCELLED
    private String status;

    public Appointment() {}

    public Appointment(User user, Doctor doctor, TimeSlot timeSlot, String status) {
        this.user = user;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    // ---- Getters & Setters ----

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public TimeSlot getTimeSlot() { return timeSlot; }
    public void setTimeSlot(TimeSlot timeSlot) { this.timeSlot = timeSlot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
