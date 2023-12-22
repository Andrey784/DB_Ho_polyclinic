package com.example.db_polyclinic_fx.Oms;

public class Oms {
    private int id;
    private String seria;
    private String num;
    private String org;

    public Oms(int id, String seria, String num, String org) {
        this.id = id;
        this.seria = seria;
        this.num = num;
        this.org = org;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return seria +" " + num + " " + org;
    }
}
