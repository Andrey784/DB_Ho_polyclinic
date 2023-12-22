package com.example.db_polyclinic_fx.Mkb;

public class Mkb {
    private String code_diagnosis;
    private String name_diagnosis;

    public Mkb(String code_diagnosis, String name_diagnosis) {
        this.code_diagnosis = code_diagnosis;
        this.name_diagnosis = name_diagnosis;
    }
    public Mkb(String name_diagnosis) {
        this.name_diagnosis = name_diagnosis;
    }

    public String getCode_diagnosis() {
        return code_diagnosis;
    }

    public void setCode_diagnosis(String code_diagnosis) {
        this.code_diagnosis = code_diagnosis;
    }

    public String getName_diagnosis() {
        return name_diagnosis;
    }

    public void setName_diagnosis(String name_diagnosis) {
        this.name_diagnosis = name_diagnosis;
    }

    @Override
    public String toString() {
        return "MKB10{" +
                "code_diagnosis = " + code_diagnosis +
                ", name_diagnosis = " + name_diagnosis + "}";
    }
}
