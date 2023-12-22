package com.example.db_polyclinic_fx.doctor;

public class Speciality {
    private int id_speciality;
    private String name_speciality;


    public Speciality(int id_speciality, String name_speciality) {
        this.id_speciality = id_speciality;
        this.name_speciality = name_speciality;
    }

    public int getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(int id_speciality) {
        this.id_speciality = id_speciality;
    }

    public String getName_speciality() {
        return name_speciality;
    }

    public void setName_speciality(String name_speciality) {
        this.name_speciality = name_speciality;
    }
}
