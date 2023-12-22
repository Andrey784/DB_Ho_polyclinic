package com.example.db_polyclinic_fx.medcart;

import java.time.LocalDate;

public class Medcart {
    private int id_medcard;
    private LocalDate date_create;
    private int id_patient;

    public Medcart(LocalDate date_create, int id_patient,int id_medcard) {
        this.date_create = date_create;
        this.id_patient = id_patient;
        this.id_medcard = id_medcard;
    }
    public Medcart(LocalDate date_create, int id_patient) {
        this.date_create = date_create;
        this.id_patient = id_patient;
    }

    public int getId_medcard() {
        return id_medcard;
    }

    public void setId_medcard(int id_medcard) {
        this.id_medcard = id_medcard;
    }

    public LocalDate getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDate date_create) {
        this.date_create = date_create;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Medcart{" +
                "id_medcard = " + id_medcard +
                ", date_create = " + date_create +
                ", id_patient = " + id_patient  + "}";
    }
}
