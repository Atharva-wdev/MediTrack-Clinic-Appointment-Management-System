package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BillingService {

    private final List<Bill> bills;

    public BillingService() {
        this.bills = new ArrayList<>();
    }

    public Bill generateBill(Appointment appointment,
                             double consultationFee,
                             double discount) {

        Validator.validateAmount(consultationFee);
        Validator.validateAmount(discount);

        Bill bill = new Bill(
                IdGenerator.getInstance().generateBillId(),
                appointment,
                consultationFee,
                discount
        );

        bills.add(bill);

        return bill;
    }

    public Optional<Bill> findBillById(String billId) {

        return bills.stream()
                .filter(b -> b.getBillId().equalsIgnoreCase(billId))
                .findFirst();
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public BillSummary generateBillSummary(Bill bill) {

        return new BillSummary(
                bill.getBillId(),
                bill.getAppointment().getPatient().getName(),
                bill.getAppointment().getDoctor().getName(),
                bill.calculateTotalAmount()
        );
    }

    public double getTotalRevenue() {

        return bills.stream()
                .mapToDouble(Bill::calculateTotalAmount)
                .sum();
    }

    public long getBillCount() {
        return bills.size();
    }

    public boolean isEmpty() {
        return bills.isEmpty();
    }

    public void displayBills() {

        if (bills.isEmpty()) {
            System.out.println("\nNo bills generated.\n");
            return;
        }

        System.out.println("\n==================== BILLS ====================\n");

        bills.forEach(System.out::println);

        System.out.println("\n===============================================\n");
    }
}