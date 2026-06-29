package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorService implements Searchable<Doctor> {

    private final List<Doctor> doctors;

    public DoctorService() {
        this.doctors = new ArrayList<>();
    }

    public Doctor addDoctor(Doctor doctor) {

        Validator.validateName(doctor.getName());
        Validator.validateAge(doctor.getAge());
        Validator.validatePhone(doctor.getPhoneNumber());
        Validator.validateAmount(doctor.getConsultationFee());

        if (doctor.getId() == null || doctor.getId().isBlank()) {
            doctor.setId(IdGenerator.getInstance().generateDoctorId());
        }

        doctors.add(doctor);
        return doctor;
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public Optional<Doctor> findById(String doctorId) {

        return doctors.stream()
                .filter(d -> d.getId().equalsIgnoreCase(doctorId))
                .findFirst();
    }

    public boolean updateDoctor(String doctorId, Doctor updatedDoctor) {

        Optional<Doctor> optionalDoctor = findById(doctorId);

        if (optionalDoctor.isEmpty()) {
            return false;
        }

        Doctor doctor = optionalDoctor.get();

        Validator.validateName(updatedDoctor.getName());
        Validator.validateAge(updatedDoctor.getAge());
        Validator.validatePhone(updatedDoctor.getPhoneNumber());
        Validator.validateAmount(updatedDoctor.getConsultationFee());

        doctor.setName(updatedDoctor.getName());
        doctor.setAge(updatedDoctor.getAge());
        doctor.setGender(updatedDoctor.getGender());
        doctor.setPhoneNumber(updatedDoctor.getPhoneNumber());
        doctor.setSpecialization(updatedDoctor.getSpecialization());
        doctor.setConsultationFee(updatedDoctor.getConsultationFee());

        return true;
    }

    public boolean deleteDoctor(String doctorId) {

        return doctors.removeIf(d ->
                d.getId().equalsIgnoreCase(doctorId));
    }

    @Override
    public List<Doctor> search(String keyword) {

        String searchKey = keyword.toLowerCase();

        return doctors.stream()
                .filter(d ->
                        d.getId().toLowerCase().contains(searchKey)
                                || d.getName().toLowerCase().contains(searchKey)
                                || d.getSpecialization().name().toLowerCase().contains(searchKey))
                .collect(Collectors.toList());
    }

    public List<Doctor> sortByName() {

        return doctors.stream()
                .sorted(Comparator.comparing(Doctor::getName))
                .collect(Collectors.toList());
    }

    public List<Doctor> sortByConsultationFee() {

        return doctors.stream()
                .sorted(Comparator.comparingDouble(Doctor::getConsultationFee))
                .collect(Collectors.toList());
    }

    public double getAverageConsultationFee() {

        return doctors.stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0.0);
    }

    public long getDoctorCount() {
        return doctors.size();
    }

    public boolean isEmpty() {
        return doctors.isEmpty();
    }

    public void displayDoctors() {

        if (doctors.isEmpty()) {
            System.out.println("\nNo doctors available.\n");
            return;
        }

        System.out.println("\n==================== DOCTORS ====================");

        doctors.forEach(System.out::println);

        System.out.println("=================================================\n");
    }
}