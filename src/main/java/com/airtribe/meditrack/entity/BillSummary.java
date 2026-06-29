package com.airtribe.meditrack.entity;

public final class BillSummary {

    private final String billId;
    private final String patientName;
    private final String doctorName;
    private final double totalAmount;

    public BillSummary(String billId,
                       String patientName,
                       String doctorName,
                       double totalAmount) {

        this.billId = billId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "BillSummary{" +
                "billId='" + billId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}