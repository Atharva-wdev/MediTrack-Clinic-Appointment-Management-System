package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Gender;

public abstract class Person {

    private String id;
    private String name;
    private int age;
    private Gender gender;
    private String phoneNumber;

    public Person() {
    }

    public Person(String id, String name, int age, Gender gender, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age must be greater than zero.");
        }
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must contain 10 digits.");
        }
        this.phoneNumber = phoneNumber;
    }

    public abstract void displayInfo();
}