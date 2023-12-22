package com.example.db_polyclinic_fx.Patient;

import java.time.LocalDate;

public class Patient {
    private String first_name;
    private String middle_name;
    private String last_name;
    private LocalDate date_birth;
    private String snils;
    private String phone_number;
    private String gender;
    private String passport_data;
    private int id_oms;
    private int id_patient;

    public Patient(String first_name, String middle_name, String last_name, LocalDate date_birth, String snils, String phone_number, String gender, int id_oms) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.snils = snils;
        this.phone_number = phone_number;
        this.gender = gender;
        this.id_oms = id_oms;
    }
    public Patient(String first_name, String middle_name, String last_name, LocalDate date_birth, String snils, String phone_number, String gender, int id_oms, int id_patient) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.snils = snils;
        this.phone_number = phone_number;
        this.gender = gender;
        this.id_oms = id_oms;
        this.id_patient = id_patient;

    }
    public Patient(String first_name, String middle_name, String last_name, LocalDate date_birth, String snils, String phone_number, String gender, int id_oms, int id_patient, String passport_data) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.snils = snils;
        this.phone_number = phone_number;
        this.gender = gender;
        this.id_oms = id_oms;
        this.id_patient = id_patient;
        this.passport_data = passport_data;

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(LocalDate date_birth) {
        this.date_birth = date_birth;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public void setPassport_data(String passport_data) {
        this.passport_data = passport_data;
    }

    public int getId_oms() {
        return id_oms;
    }

    public void setId_oms(int id_oms) {
        this.id_oms = id_oms;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id_patient = " + id_patient +
                ", first_name = " + first_name +
                ", middle_name = " + middle_name +
                ", last_name = " + last_name +
                ", date_birth = " + date_birth +
                ", snils = " + snils +
                ", phone_number = " + phone_number +
                ", id_oms = " + id_oms +
                ", gender = " + gender +
                 "}";
    }
}
