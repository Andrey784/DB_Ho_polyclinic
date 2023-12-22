package com.example.db_polyclinic_fx.doctor;

public class Doctor {
    private String first_name;
    private String middle_name;
    private String last_name;
    private String login;
    private String password_doctor;
    private int id_speciality;
    private int id_doctor;



    public Doctor(String first_name, String middle_name, String last_name, String login, String password_doctor, int id_speciality) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.login = login;
        this.password_doctor = password_doctor;
        this.id_speciality = id_speciality;
    }


    public Doctor(String first_name, String middle_name, String last_name, String login, String password_doctor, int id_speciality, int id_doctor) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.login = login;
        this.password_doctor = password_doctor;
        this.id_speciality = id_speciality;
        this.id_doctor = id_doctor;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword_doctor() {
        return password_doctor;
    }

    public void setPassword_doctor(String password_doctor) {
        this.password_doctor = password_doctor;
    }

    public int getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(int id_speciality) {
        this.id_speciality = id_speciality;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }
}
