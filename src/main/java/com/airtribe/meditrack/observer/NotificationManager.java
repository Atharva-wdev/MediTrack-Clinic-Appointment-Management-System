package com.airtribe.meditrack.observer;

import com.airtribe.meditrack.entity.Appointment;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {

    private final List<AppointmentObserver> observers;

    public NotificationManager() {
        observers = new ArrayList<>();
    }

    public void addObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AppointmentObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Appointment appointment) {

        for (AppointmentObserver observer : observers) {
            observer.update(appointment);
        }

    }
}