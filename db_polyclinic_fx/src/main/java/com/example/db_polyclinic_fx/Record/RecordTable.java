package com.example.db_polyclinic_fx.Record;

public class RecordTable {
    private String date_record;
    private String complaints;
    private String id_medcard;
    private String doctor;

    public RecordTable(String date_record, String complaints, String id_medcard, String doctor) {
        this.date_record = date_record;
        this.complaints = complaints;
        this.id_medcard = id_medcard;
        this.doctor = doctor;
    }

    public String getDate_record() {
        return date_record;
    }

    public void setDate_record(String date_record) {
        this.date_record = date_record;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getId_medcard() {
        return id_medcard;
    }

    public void setId_medcard(String id_medcard) {
        this.id_medcard = id_medcard;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
