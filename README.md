# 🏥 Clinic Appointment Management System

A full-stack **Clinic Appointment Management System** built using **Spring Boot (Java)** and **Thymeleaf**, designed to streamline patient appointment booking and doctor management.

---

## 🚀 Project Overview

This application automates clinic operations by providing a digital platform where:

* Patients can **book appointments easily**
* Doctors’ schedules are **managed efficiently**
* Admin can **control doctors and time slots**

The system follows a **clean MVC architecture** and uses **Spring Data JPA + Hibernate** for database operations.

---

## 🛠️ Tech Stack

| Layer        | Technology                            |
| ------------ | ------------------------------------- |
| Backend      | Spring Boot (Java 17)                 |
| ORM          | Spring Data JPA + Hibernate           |
| Database     | MySQL                                 |
| Frontend     | HTML, CSS, Bootstrap, Thymeleaf       |
| Architecture | MVC (Controller, Service, Repository) |

---

## 👥 User Roles

### 👤 Patient

* Register & Login
* View doctors
* Book appointment (Doctor → Slot → Confirm)
* View appointments
* Cancel appointments

### 🛠️ Admin

* Login
* Add / Delete doctors
* Manage time slots
* View all appointments

---

## ✨ Features

* ✔️ User Authentication (Login/Register)
* ✔️ Doctor Management
* ✔️ Time Slot Management
* ✔️ Appointment Booking System
* ✔️ Cancel Appointment
* ✔️ Prevent Double Booking
* ✔️ Clean UI using Bootstrap
* ✔️ MVC Architecture Implementation

---

## 🧩 System Workflow

1. Patient logs in
2. Selects doctor
3. Views available time slots
4. Books appointment
5. Slot marked as **booked**
6. Admin manages doctors & slots

---

## 🗄️ Database Design

### Entities:

#### 1. User

* id
* name
* email
* password
* role (PATIENT / ADMIN)

#### 2. Doctor

* id
* name
* specialization

#### 3. TimeSlot

* id
* doctor (ManyToOne)
* startTime
* endTime
* isBooked

#### 4. Appointment

* id
* user (ManyToOne)
* doctor (ManyToOne)
* timeSlot (OneToOne)
* status (BOOKED / CANCELLED)

---

## 🔗 Relationships

* One Doctor → Many TimeSlots
* One User → Many Appointments
* One Appointment → One TimeSlot

---

## 🏗️ Project Structure

```bash
src/main/java/com/clinic/clinic_management
│
├── controller
│   ├── AuthController.java
│   ├── PatientController.java
│   └── AdminController.java
│
├── service
│   ├── UserService.java
│   ├── DoctorService.java
│   └── AppointmentService.java
│
├── repository
│   ├── UserRepository.java
│   ├── DoctorRepository.java
│   ├── TimeSlotRepository.java
│   └── AppointmentRepository.java
│
├── entity
│   ├── User.java
│   ├── Doctor.java
│   ├── TimeSlot.java
│   └── Appointment.java
│
└── config
    └── DataLoader.java
```

---

## 🌐 Frontend Pages

* Login Page
* Register Page
* Patient Dashboard
* Doctor List
* Time Slot Selection
* My Appointments
* Admin Dashboard
* Add Doctor
* Manage Time Slots

---

## ▶️ How to Run the Project

### 1️⃣ Clone Repository

```bash
git clone https://github.com/Saurabh9922/clinic-management-system.git
```

### 2️⃣ Create Database

```sql
CREATE DATABASE clinic_db_Resume;
```

### 3️⃣ Configure application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinic_db_Resume
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 4️⃣ Run Project

```bash
mvn spring-boot:run
```

---

### 5️⃣ Open in Browser

```
http://localhost:8080
```

---

## 🔥 Sample Data

* Default Admin account is auto-created
* Doctors and time slots are loaded automatically using DataLoader
  👉 Verified from logs: data initialization runs on startup 

---

## 📸 Screenshots
<img width="1918" height="907" alt="Screenshot 2026-04-23 141556" src="https://github.com/user-attachments/assets/3f497d72-2465-4af3-af7a-abd039c59c5b" />
<img width="1913" height="908" alt="Screenshot 2026-04-23 141612" src="https://github.com/user-attachments/assets/31d1ca3d-03e3-4ee0-b9f9-a450f71e256e" />


---

## 🚀 Future Improvements

* 🔐 Spring Security authentication
* 🌐 Deployment (Render / Railway / AWS)
* 💳 Online payment integration
* 📱 Responsive UI improvements
* 📊 Analytics dashboard

---

## 👨‍💻 Author

**Saurabh Mapari**

* 🎓 M.Tech Computer Engineering (PICT Pune)
* 💻 Skills: Java, Spring Boot, SQL, Machine Learning



---
