package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PatientService implements Searchable<Patient> {

    private final List<Patient> patients;

    public PatientService() {
        this.patients = new ArrayList<>();
    }

    public Patient addPatient(Patient patient) {

        Validator.validateName(patient.getName());
        Validator.validateAge(patient.getAge());
        Validator.validatePhone(patient.getPhoneNumber());

        if (patient.getId() == null || patient.getId().isBlank()) {
            patient.setId(IdGenerator.getInstance().generatePatientId());
        }

        patients.add(patient);

        return patient;
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public Optional<Patient> findById(String patientId) {

        return patients.stream()
                .filter(patient -> patient.getId().equalsIgnoreCase(patientId))
                .findFirst();
    }

    public boolean updatePatient(String patientId, Patient updatedPatient) {

        Optional<Patient> optionalPatient = findById(patientId);

        if (optionalPatient.isEmpty()) {
            return false;
        }

        Patient patient = optionalPatient.get();

        Validator.validateName(updatedPatient.getName());
        Validator.validateAge(updatedPatient.getAge());
        Validator.validatePhone(updatedPatient.getPhoneNumber());

        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        patient.setMedicalHistory(updatedPatient.getMedicalHistory());

        return true;
    }

    public boolean deletePatient(String patientId) {

        return patients.removeIf(patient ->
                patient.getId().equalsIgnoreCase(patientId));
    }

    @Override
    public List<Patient> search(String keyword) {

        String searchKey = keyword.toLowerCase();

        return patients.stream()
                .filter(patient ->
                        patient.getId().toLowerCase().contains(searchKey)
                                || patient.getName().toLowerCase().contains(searchKey)
                                || patient.getMedicalHistory().toLowerCase().contains(searchKey))
                .collect(Collectors.toList());
    }

    public List<Patient> sortByName() {

        return patients.stream()
                .sorted(Comparator.comparing(Patient::getName))
                .collect(Collectors.toList());
    }

    public long getPatientCount() {
        return patients.size();
    }

    public boolean isEmpty() {
        return patients.isEmpty();
    }

    public void displayPatients() {

        if (patients.isEmpty()) {
            System.out.println("\nNo patients available.\n");
            return;
        }

        System.out.println("\n==================== PATIENTS ====================");

        patients.forEach(System.out::println);

        System.out.println("==================================================\n");
    }
}