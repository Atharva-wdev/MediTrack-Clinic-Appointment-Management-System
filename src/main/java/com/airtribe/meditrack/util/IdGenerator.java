package com.airtribe.meditrack.util;

public final class IdGenerator {

    private static final IdGenerator INSTANCE = new IdGenerator();

    private int doctorCounter = 1;
    private int patientCounter = 1;
    private int appointmentCounter = 1;
    private int billCounter = 1;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String generateDoctorId() {
        return String.format("DOC%03d", doctorCounter++);
    }

    public String generatePatientId() {
        return String.format("PAT%03d", patientCounter++);
    }

    public String generateAppointmentId() {
        return String.format("APT%03d", appointmentCounter++);
    }

    public String generateBillId() {
        return String.format("BILL%03d", billCounter++);
    }
}