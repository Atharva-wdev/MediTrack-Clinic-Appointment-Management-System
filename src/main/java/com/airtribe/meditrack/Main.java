package com.airtribe.meditrack;

import com.airtribe.meditrack.controller.ClinicController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ClinicController controller = new ClinicController();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {

            printMenu();

            System.out.print("\nEnter your choice : ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input.");
                continue;
            }

            try {

                switch (choice) {

                    // Doctor Module
                    case 1 -> controller.addDoctor();
                    case 2 -> controller.viewDoctors();
                    case 3 -> controller.searchDoctor();
                    case 4 -> controller.deleteDoctor();

                    // Patient Module
                    case 5 -> controller.addPatient();
                    case 6 -> controller.viewPatients();
                    case 7 -> controller.searchPatient();
                    case 8 -> controller.deletePatient();

                    // Appointment Module
                    case 9 -> controller.bookAppointment();
                    case 10 -> controller.viewAppointments();
                    case 11 -> controller.confirmAppointment();
                    case 12 -> controller.completeAppointment();
                    case 13 -> controller.cancelAppointment();
                    case 14 -> controller.searchAppointmentByDoctor();
                    case 15 -> controller.searchAppointmentByPatient();

                    // Billing Module
                    case 16 -> controller.generateBill();
                    case 17 -> controller.viewBills();
                    case 18 -> controller.displayBillSummary();

                    case 0 -> {
                        exit = true;
                        controller.close();
                        System.out.println("\nThank you for using MediTrack.");
                    }

                    default -> System.out.println("\nInvalid Choice.");

                }

            } catch (Exception ex) {

                System.out.println("\nError : " + ex.getMessage());

            }

        }

        scanner.close();
    }

    private static void printMenu() {

        System.out.println("\n==================================================");
        System.out.println("        MEDITRACK CLINIC MANAGEMENT SYSTEM");
        System.out.println("==================================================");

        System.out.println("\n--------------- Doctor Module ----------------");
        System.out.println("1.  Add Doctor");
        System.out.println("2.  View Doctors");
        System.out.println("3.  Search Doctor");
        System.out.println("4.  Delete Doctor");

        System.out.println("\n--------------- Patient Module ----------------");
        System.out.println("5.  Add Patient");
        System.out.println("6.  View Patients");
        System.out.println("7.  Search Patient");
        System.out.println("8.  Delete Patient");

        System.out.println("\n------------- Appointment Module --------------");
        System.out.println("9.  Book Appointment");
        System.out.println("10. View Appointments");
        System.out.println("11. Confirm Appointment");
        System.out.println("12. Complete Appointment");
        System.out.println("13. Cancel Appointment");
        System.out.println("14. Search Appointment By Doctor");
        System.out.println("15. Search Appointment By Patient");

        System.out.println("\n---------------- Billing Module ----------------");
        System.out.println("16. Generate Bill");
        System.out.println("17. View Bills");
        System.out.println("18. Display Bill Summary");

        System.out.println("\n0. Exit");

        System.out.println("==================================================");
    }
}