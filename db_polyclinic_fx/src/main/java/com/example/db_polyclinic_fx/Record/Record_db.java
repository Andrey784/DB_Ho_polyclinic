package com.example.db_polyclinic_fx.Record;

import java.time.LocalDate;

public class Record_db {
    private int id_record;
    private LocalDate date_record;
    private String complaints;
    private int id_medcard;
    private int id_doctor;

    public Record_db(LocalDate date_record, String complaints, int id_medcard, int id_doctor, int id_record) {
        this.date_record = date_record;
        this.complaints = complaints;
        this.id_medcard = id_medcard;
        this.id_doctor = id_doctor;
        this.id_record = id_record;

    }

    public Record_db(LocalDate date_record, String complaints, int id_medcard, int id_doctor) {
        this.date_record = date_record;
        this.complaints = complaints;
        this.id_medcard = id_medcard;
        this.id_doctor = id_doctor;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    public LocalDate getDate_record() {
        return date_record;
    }

    public void setDate_record(LocalDate date_record) {
        this.date_record = date_record;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public int getId_medcard() {
        return id_medcard;
    }

    public void setId_medcard(int id_medcard) {
        this.id_medcard = id_medcard;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id_record = " + id_record +
                ", date_record = " + date_record +
                ", complaints = " + complaints +
                ", id_medcard = " + id_medcard +
                ", id_doctor = " + id_doctor + "}";
    }

}
