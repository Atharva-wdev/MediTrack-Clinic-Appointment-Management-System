package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.interfaces.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {

    private final List<Doctor> doctors = new ArrayList<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor findById(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public boolean removeDoctor(String id) {
        return doctors.removeIf(d -> d.getId().equalsIgnoreCase(id));
    }

    @Override
    public List<Doctor> search(String keyword) {
        return doctors.stream()
                .filter(d ->
                        d.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                                d.getSpecialization().name().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}