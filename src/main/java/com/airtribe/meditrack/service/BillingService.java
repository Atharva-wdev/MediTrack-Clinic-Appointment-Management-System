package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.BillSummary;
import com.airtribe.meditrack.util.IdGenerator;

public class BillingService {

    public Bill generateBill(Appointment appointment,
                             double consultationFee,
                             double discount) {

        return new Bill(
                IdGenerator.getInstance().generateBillId(),
                appointment,
                consultationFee,
                discount
        );
    }

    public BillSummary generateSummary(Bill bill) {

        return new BillSummary(
                bill.getBillId(),
                bill.getAppointment().getPatient().getName(),
                bill.getAppointment().getDoctor().getName(),
                bill.calculateTotalAmount()
        );
    }
}