package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.enums.Specialization;

public class Doctor extends Person {

    private Specialization specialization;
    private double consultationFee;

    public Doctor() {
    }

    public Doctor(String id,
                  String name,
                  int age,
                  Gender gender,
                  String phoneNumber,
                  Specialization specialization,
                  double consultationFee) {

        super(id, name, age, gender, phoneNumber);

        this.specialization = specialization;
        this.consultationFee = consultationFee;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        if (consultationFee < 0) {
            throw new IllegalArgumentException("Consultation fee cannot be negative.");
        }
        this.consultationFee = consultationFee;
    }

    @Override
    public void displayInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", specialization=" + specialization +
                ", consultationFee=" + consultationFee +
                '}';
    }
}