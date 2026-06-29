package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

public final class Validator {

    private Validator() {
    }

    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Name cannot be empty.");
        }
    }

    public static void validateAge(int age) {
        if (age <= 0 || age > 120) {
            throw new InvalidDataException("Invalid age.");
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidDataException("Phone number must contain exactly 10 digits.");
        }
    }

    public static void validateAmount(double amount) {
        if (amount < 0) {
            throw new InvalidDataException("Amount cannot be negative.");
        }
    }
}