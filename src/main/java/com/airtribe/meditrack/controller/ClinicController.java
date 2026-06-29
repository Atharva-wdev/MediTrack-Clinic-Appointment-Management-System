package com.airtribe.meditrack.controller;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.Gender;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.BillingService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.util.DateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClinicController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final BillingService billingService;

    private final Scanner scanner;

    public ClinicController() {

        doctorService = new DoctorService();
        patientService = new PatientService();
        appointmentService = new AppointmentService();
        billingService = new BillingService();

        scanner = new Scanner(System.in);
    }

    /*=========================================================
                        DOCTOR MODULE
     =========================================================*/

    public void addDoctor() {

        System.out.println("\n========== Add Doctor ==========");

        System.out.print("Doctor Name : ");
        String name = scanner.nextLine();

        System.out.print("Age : ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("\nGender");

        for (Gender gender : Gender.values()) {
            System.out.println("- " + gender);
        }

        System.out.print("Select Gender : ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Phone Number : ");
        String phone = scanner.nextLine();

        System.out.println("\nSpecializations");

        for (Specialization specialization : Specialization.values()) {
            System.out.println("- " + specialization);
        }

        System.out.print("Select Specialization : ");

        Specialization specialization =
                Specialization.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Consultation Fee : ");

        double fee = Double.parseDouble(scanner.nextLine());

        Doctor doctor = new Doctor(
                "",
                name,
                age,
                gender,
                phone,
                specialization,
                fee
        );

        doctorService.addDoctor(doctor);

        System.out.println("\nDoctor Added Successfully.");
        System.out.println("Doctor ID : " + doctor.getId());
    }

    public void viewDoctors() {

        System.out.println("\n========== Doctors ==========\n");

        List<Doctor> doctors = doctorService.getAllDoctors();

        if (doctors.isEmpty()) {

            System.out.println("No doctors available.");

            return;
        }

        doctors.forEach(System.out::println);
    }

    public void searchDoctor() {

        System.out.print("\nEnter Name / Specialization : ");

        String keyword = scanner.nextLine();

        List<Doctor> doctors = doctorService.search(keyword);

        if (doctors.isEmpty()) {

            System.out.println("No Doctor Found.");

            return;
        }

        doctors.forEach(System.out::println);
    }

    public void deleteDoctor() {

        System.out.print("\nEnter Doctor ID : ");

        String doctorId = scanner.nextLine();

        boolean deleted = doctorService.deleteDoctor(doctorId);

        if (deleted) {

            System.out.println("Doctor Deleted Successfully.");

        } else {

            System.out.println("Doctor Not Found.");
        }
    }
    /*=========================================================
                        PATIENT MODULE
     =========================================================*/

    public void addPatient() {

        System.out.println("\n========== Add Patient ==========");

        System.out.print("Patient Name : ");
        String name = scanner.nextLine();

        System.out.print("Age : ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("\nGender");

        for (Gender gender : Gender.values()) {
            System.out.println("- " + gender);
        }

        System.out.print("Select Gender : ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Phone Number : ");
        String phone = scanner.nextLine();

        System.out.print("Medical History : ");
        String history = scanner.nextLine();

        Patient patient = new Patient(
                "",
                name,
                age,
                gender,
                phone,
                history
        );

        patientService.addPatient(patient);

        System.out.println("\nPatient Added Successfully.");
        System.out.println("Patient ID : " + patient.getId());
    }

    public void viewPatients() {

        System.out.println("\n========== Patients ==========\n");

        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {

            System.out.println("No patients available.");

            return;
        }

        patients.forEach(System.out::println);
    }

    public void searchPatient() {

        System.out.print("\nEnter Patient Name : ");

        String keyword = scanner.nextLine();

        List<Patient> patients = patientService.search(keyword);

        if (patients.isEmpty()) {

            System.out.println("No Patient Found.");

            return;
        }

        patients.forEach(System.out::println);
    }

    public void deletePatient() {

        System.out.print("\nEnter Patient ID : ");

        String patientId = scanner.nextLine();

        boolean deleted = patientService.deletePatient(patientId);

        if (deleted) {

            System.out.println("Patient Deleted Successfully.");

        } else {

            System.out.println("Patient Not Found.");
        }
    }
        /*=========================================================
                    APPOINTMENT MODULE
     =========================================================*/

    public void bookAppointment() {

        System.out.println("\n========== Book Appointment ==========\n");

        if (doctorService.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        if (patientService.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        System.out.print("Enter Doctor ID : ");
        String doctorId = scanner.nextLine();

        Optional<Doctor> doctorOptional = doctorService.findById(doctorId);

        if (doctorOptional.isEmpty()) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Enter Patient ID : ");
        String patientId = scanner.nextLine();

        Optional<Patient> patientOptional = patientService.findById(patientId);

        if (patientOptional.isEmpty()) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Appointment Date (dd-MM-yyyy): ");
        String dateInput = scanner.nextLine();

        System.out.print("Appointment Time (HH:mm): ");
        String timeInput = scanner.nextLine();

        System.out.print("Remarks : ");
        String remarks = scanner.nextLine();

        Appointment appointment = appointmentService.bookAppointment(
                doctorOptional.get(),
                patientOptional.get(),
                DateUtil.parseDate(dateInput),
                DateUtil.parseTime(timeInput),
                remarks
        );

        System.out.println("\nAppointment Booked Successfully.");
        System.out.println("Appointment ID : " + appointment.getAppointmentId());
    }

    public void viewAppointments() {

        System.out.println("\n========== Appointments ==========\n");

        List<Appointment> appointments =
                appointmentService.getAllAppointments();

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        appointments.forEach(System.out::println);
    }

    public void confirmAppointment() {

        System.out.print("Enter Appointment ID : ");

        String appointmentId = scanner.nextLine();

        appointmentService.confirmAppointment(appointmentId);

        System.out.println("Appointment Confirmed.");
    }

    public void completeAppointment() {

        System.out.print("Enter Appointment ID : ");

        String appointmentId = scanner.nextLine();

        appointmentService.completeAppointment(appointmentId);

        System.out.println("Appointment Completed.");
    }

    public void cancelAppointment() {

        System.out.print("Enter Appointment ID : ");

        String appointmentId = scanner.nextLine();

        appointmentService.cancelAppointment(appointmentId);

        System.out.println("Appointment Cancelled.");
    }

    public void searchAppointmentByDoctor() {

        System.out.print("Enter Doctor Name : ");

        String doctorName = scanner.nextLine();

        List<Appointment> appointments =
                appointmentService.searchByDoctor(doctorName);

        if (appointments.isEmpty()) {

            System.out.println("No appointments found.");

            return;
        }

        appointments.forEach(System.out::println);
    }

    public void searchAppointmentByPatient() {

        System.out.print("Enter Patient Name : ");

        String patientName = scanner.nextLine();

        List<Appointment> appointments =
                appointmentService.searchByPatient(patientName);

        if (appointments.isEmpty()) {

            System.out.println("No appointments found.");

            return;
        }

        appointments.forEach(System.out::println);
    }

        /*=========================================================
                        BILLING MODULE
     =========================================================*/

    public void generateBill() {

        System.out.println("\n========== Generate Bill ==========\n");

        if (appointmentService.isEmpty()) {

            System.out.println("No appointments available.");

            return;
        }

        System.out.print("Enter Appointment ID : ");

        String appointmentId = scanner.nextLine();

        Optional<Appointment> appointment =
                appointmentService.findById(appointmentId);

        if (appointment.isEmpty()) {

            System.out.println("Appointment Not Found.");

            return;
        }

        System.out.print("Consultation Fee : ");

        double consultationFee =
                Double.parseDouble(scanner.nextLine());

        System.out.print("Discount : ");

        double discount =
                Double.parseDouble(scanner.nextLine());

        Bill bill = billingService.generateBill(
                appointment.get(),
                consultationFee,
                discount
        );

        System.out.println("\nBill Generated Successfully.");

        System.out.println(bill);
    }

    public void viewBills() {

        System.out.println("\n========== Bills ==========\n");

        List<Bill> bills = billingService.getAllBills();

        if (bills.isEmpty()) {

            System.out.println("No Bills Generated.");

            return;
        }

        bills.forEach(System.out::println);
    }

    public void displayBillSummary() {

        System.out.print("Enter Bill ID : ");

        String billId = scanner.nextLine();

        Optional<Bill> bill =
                billingService.findBillById(billId);

        if (bill.isEmpty()) {

            System.out.println("Bill Not Found.");

            return;
        }

        BillSummary summary =
                billingService.generateBillSummary(bill.get());

        System.out.println();

        System.out.println(summary);
    }

    public void close() {

        scanner.close();
    }

}