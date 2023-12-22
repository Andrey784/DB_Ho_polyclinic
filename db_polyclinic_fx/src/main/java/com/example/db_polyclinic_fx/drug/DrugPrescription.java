package com.example.db_polyclinic_fx.drug;

public class DrugPrescription {
    private int id_drug;
    private int id_prescription;

    public DrugPrescription(int id_drug, int id_prescription) {
        this.id_drug = id_drug;
        this.id_prescription = id_prescription;
    }

    public int getId_drug() {
        return id_drug;
    }

    public void setId_drug(int id_drug) {
        this.id_drug = id_drug;
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }

    @Override
    public String toString() {
        return "DrugPrescription{" +
                "id_drug=" + id_drug +
                ", id_prescription='" + id_prescription + '\'' +
               '}';
    }

}
