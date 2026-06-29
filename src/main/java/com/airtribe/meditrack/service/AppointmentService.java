package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.observer.ConsoleNotificationObserver;
import com.airtribe.meditrack.observer.NotificationManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppointmentService {

    private final List<Appointment> appointments;
    private final NotificationManager notificationManager;

    public AppointmentService() {
        this.appointments = new ArrayList<>();
        this.notificationManager = new NotificationManager();

        notificationManager.addObserver(
                new ConsoleNotificationObserver()
        );
    }

    public Appointment bookAppointment(Doctor doctor,
                                       Patient patient,
                                       LocalDate date,
                                       LocalTime time,
                                       String remarks) {

        Appointment appointment = new Appointment(
                IdGenerator.getInstance().generateAppointmentId(),
                doctor,
                patient,
                date,
                time,
                AppointmentStatus.PENDING,
                remarks
        );

        appointments.add(appointment);
        notificationManager.notifyObservers(appointment);
        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public Optional<Appointment> findById(String appointmentId) {

        return appointments.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(appointmentId))
                .findFirst();
    }

    public void confirmAppointment(String appointmentId) {

        Appointment appointment = findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found."));

        appointment.setStatus(AppointmentStatus.CONFIRMED);
    }

    public void cancelAppointment(String appointmentId) {

        Appointment appointment = findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found."));

        appointment.setStatus(AppointmentStatus.CANCELLED);
    }

    public void completeAppointment(String appointmentId) {

        Appointment appointment = findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found."));

        appointment.setStatus(AppointmentStatus.COMPLETED);
    }

    public boolean deleteAppointment(String appointmentId) {

        return appointments.removeIf(a ->
                a.getAppointmentId().equalsIgnoreCase(appointmentId));
    }

    public List<Appointment> searchByPatient(String patientName) {

        return appointments.stream()
                .filter(a ->
                        a.getPatient().getName()
                                .toLowerCase()
                                .contains(patientName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Appointment> searchByDoctor(String doctorName) {

        return appointments.stream()
                .filter(a ->
                        a.getDoctor().getName()
                                .toLowerCase()
                                .contains(doctorName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByStatus(AppointmentStatus status) {

        return appointments.stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Appointment> sortByDate() {

        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate)
                        .thenComparing(Appointment::getAppointmentTime))
                .collect(Collectors.toList());
    }

    public long getAppointmentCount() {
        return appointments.size();
    }

    public boolean isEmpty() {
        return appointments.isEmpty();
    }

    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("\nNo appointments found.\n");
            return;
        }

        System.out.println("\n================ APPOINTMENTS ================\n");

        appointments.forEach(System.out::println);

        System.out.println("\n==============================================\n");
    }
}