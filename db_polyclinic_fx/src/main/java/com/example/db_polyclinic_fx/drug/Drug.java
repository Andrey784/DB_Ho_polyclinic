package com.example.db_polyclinic_fx.drug;

public class Drug {
    private int id_drug;
    private String name_drug;
    private String dosage;
    private String release_form;
    private String quantity;

    public Drug(int id_drug, String name_drug, String dosage, String release_form, String quantity) {
        this.id_drug = id_drug;
        this.name_drug = name_drug;
        this.dosage = dosage;
        this.release_form = release_form;
        this.quantity = quantity;
    }

    public int getId_drug() {
        return id_drug;
    }

    public void setId_drug(int id_drug) {
        this.id_drug = id_drug;
    }

    public String getName_drug() {
        return name_drug;
    }

    public void setName_drug(String name_drug) {
        this.name_drug = name_drug;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getRelease_form() {
        return release_form;
    }

    public void setRelease_form(String release_form) {
        this.release_form = release_form;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id_drug=" + id_drug +
                ", name_drug='" + name_drug + '\''
                +", dosage='" + dosage + '\''
                +", release_form='" + release_form + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
