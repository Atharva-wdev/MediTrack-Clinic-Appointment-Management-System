package com.airtribe.meditrack.factory;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.util.IdGenerator;

public final class BillFactory {

    private BillFactory() {
    }

    public static Bill createBill(Appointment appointment,
                                  double consultationFee,
                                  double discount) {

        return new Bill(
                IdGenerator.getInstance().generateBillId(),
                appointment,
                consultationFee,
                discount
        );
    }
}