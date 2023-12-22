package com.example.db_polyclinic_fx.Patient;

import java.time.LocalDate;

public class PatientTable {
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_birth;
    private String snils;
    private String phone_number;
    private String gender;
    private String oms;

    public PatientTable(String first_name, String middle_name, String last_name, String date_birth, String snils, String phone_number, String gender, String oms) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.date_birth = date_birth;
        this.snils = snils;
        this.phone_number = phone_number;
        this.gender = gender;
        this.oms = oms;
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

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
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

    public String getOms() {
        return oms;
    }

    public void setOms(String oms) {
        this.oms = oms;
    }
}
