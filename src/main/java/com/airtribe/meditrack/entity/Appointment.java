package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private String appointmentId;
    private Doctor doctor;
    private Patient patient;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentStatus status;
    private String remarks;

    public Appointment() {
    }

    public Appointment(String appointmentId,
                       Doctor doctor,
                       Patient patient,
                       LocalDate appointmentDate,
                       LocalTime appointmentTime,
                       AppointmentStatus status,
                       String remarks) {

        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.remarks = remarks;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", status=" + status +
                '}';
    }
}