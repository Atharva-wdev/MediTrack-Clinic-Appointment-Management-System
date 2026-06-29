package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;

public class ConsoleNotificationObserver implements AppointmentObserver {

    @Override
    public void update(Appointment appointment) {

        System.out.println("\n==================================");
        System.out.println("Notification");
        System.out.println("----------------------------------");
        System.out.println("Appointment Booked Successfully");
        System.out.println("Appointment ID : " + appointment.getAppointmentId());
        System.out.println("Doctor         : " + appointment.getDoctor().getName());
        System.out.println("Patient        : " + appointment.getPatient().getName());
        System.out.println("==================================\n");
    }
}