package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();

    public void bookAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public Appointment findById(String id) {

        return appointments.stream()
                .filter(a -> a.getAppointmentId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() ->
                        new AppointmentNotFoundException("Appointment not found."));
    }

    public void cancelAppointment(String id) {

        Appointment appointment = findById(id);

        appointment.setStatus(AppointmentStatus.CANCELLED);
    }

    public void completeAppointment(String id) {

        Appointment appointment = findById(id);

        appointment.setStatus(AppointmentStatus.COMPLETED);
    }
}