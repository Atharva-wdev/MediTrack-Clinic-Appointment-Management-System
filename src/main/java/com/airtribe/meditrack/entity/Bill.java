package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.AppConstants;
import com.airtribe.meditrack.interfaces.Payable;

public class Bill implements Payable {

    private String billId;
    private Appointment appointment;
    private double consultationFee;
    private double discount;

    public Bill() {
    }

    public Bill(String billId,
                Appointment appointment,
                double consultationFee,
                double discount) {

        this.billId = billId;
        this.appointment = appointment;
        this.consultationFee = consultationFee;
        this.discount = discount;
    }

    public String getBillId() {
        return billId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public double calculateTotalAmount() {

        double taxableAmount = consultationFee - discount;
        double tax = taxableAmount * AppConstants.GST_RATE;

        return taxableAmount + tax;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", consultationFee=" + consultationFee +
                ", discount=" + discount +
                ", total=" + calculateTotalAmount() +
                '}';
    }
}