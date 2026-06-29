package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {

    private final List<Patient> patients = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient findById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public boolean removePatient(String id) {
        return patients.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    @Override
    public List<Patient> search(String keyword) {
        return patients.stream()
                .filter(p ->
                        p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                                p.getMedicalHistory().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}