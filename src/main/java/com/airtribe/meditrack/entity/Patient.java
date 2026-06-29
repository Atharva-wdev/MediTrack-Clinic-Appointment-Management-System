package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Gender;

public class Patient extends Person {

    private String medicalHistory;

    public Patient() {
    }

    public Patient(String id,
                   String name,
                   int age,
                   Gender gender,
                   String phoneNumber,
                   String medicalHistory) {

        super(id, name, age, gender, phoneNumber);

        this.medicalHistory = medicalHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public void displayInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                '}';
    }
}