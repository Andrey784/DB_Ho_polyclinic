package com.example.db_polyclinic_fx.drug;

import java.time.LocalDate;

public class Prescription {
    private int id_prescription;
    private LocalDate date_prescription;
    private int period;
    private int id_record;

    public Prescription(LocalDate date_prescription, int period, int id_record, int id_prescription) {
        this.id_prescription = id_prescription;
        this.date_prescription = date_prescription;
        this.period = period;
        this.id_record = id_record;
    }
    public Prescription(LocalDate date_prescription, int period, int id_record) {
        this.date_prescription = date_prescription;
        this.period = period;
        this.id_record = id_record;
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    public LocalDate getDate_prescription() {
        return date_prescription;
    }

    public void setDate_prescription(LocalDate date_prescription) {
        this.date_prescription = date_prescription;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id_prescription = " + id_prescription +
                ", date_prescription = " + date_prescription +
                ", period = " + period +
                ", id_record = " + id_record + "}";
    }
}
