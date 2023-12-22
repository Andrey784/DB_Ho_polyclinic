package com.example.db_polyclinic_fx.diagnos;

public class Diagnos {
    private Boolean is_first;
    private String code_diagnosis;
    private int id_record;

    public Diagnos(Boolean is_first, String code_diagnosis, int id_record) {
        this.is_first = is_first;
        this.code_diagnosis = code_diagnosis;
        this.id_record = id_record;
    }
    public Diagnos(Boolean is_first, String code_diagnosis) {
        this.is_first = is_first;
        this.code_diagnosis = code_diagnosis;
    }

    public Boolean getIs_first() {
        return is_first;
    }

    public void setIs_first(Boolean is_first) {
        this.is_first = is_first;
    }

    public String getCode_diagnosis() {
        return code_diagnosis;
    }

    public void setCode_diagnosis(String code_diagnosis) {
        this.code_diagnosis = code_diagnosis;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "is_first = " + is_first +
                ", code_diagnosis = " + code_diagnosis +
                ", id_record = " + id_record + "}";
    }
}
