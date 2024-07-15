package com.example.tugas_note;

public class Student {
    private int id;
    private String name;
    private String registrationNumber;
    private String phone;
    private String email;

    // Constructor
    public Student(int id, String name, String registrationNumber, String phone, String email) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.phone = phone;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
