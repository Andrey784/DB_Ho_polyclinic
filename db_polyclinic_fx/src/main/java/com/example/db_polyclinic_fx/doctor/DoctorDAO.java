package com.example.db_polyclinic_fx.doctor;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.doctor.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private final DatabaseConnection connection;
    public DoctorDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Получение сущности по login
    public Doctor getDoctorByLogin(String login){
        String sql = "SELECT * FROM public.doctor WHERE login = ?";
        Doctor doctor = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id_doctor");
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String password_doctor = rs.getString("password_doctor");
                int id_speciality = rs.getInt("id_speciality");
                doctor = new Doctor(first_name, middle_name, last_name, login, password_doctor, id_speciality, id);            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
    // Получение сущности по login
    public Doctor getDoctorById(int id_doctor){
        String sql = "SELECT * FROM public.doctor WHERE id_doctor = ?";
        Doctor doctor = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_doctor);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id_doctor");
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String login = rs.getString("login");
                String password_doctor = rs.getString("password_doctor");
                int id_speciality = rs.getInt("id_speciality");
                doctor = new Doctor(first_name, middle_name, last_name, login, password_doctor, id_speciality, id_doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    // вывод всех сущностей
//    public void getAllDoctor() {
//        try (Connection conn = connection.connect();
//             Statement statement = conn.createStatement();
//             ResultSet rs = statement.executeQuery("SELECT * FROM public.doctor")) {
//            while (rs.next()) {
//                String first_name = rs.getString("first_name");
//                String middle_name = rs.getString("middle_name");
//                String last_name = rs.getString("last_name");
//                int id_speciality = rs.getInt("id_speciality");
//                int id_doctor = rs.getInt("id_doctor");
//
//
//                System.out.println("first_name = " + first_name +
//                        ", middle_name = " + middle_name +
//                        ", last_name = " + last_name +
//                        ", id_speciality = " + id_speciality +
//                        ", id_doctor = " + id_doctor
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorList = new ArrayList<>();

        try (Connection conn = connection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM public.doctor")) {

            while (rs.next()) {
                int id = rs.getInt("id_doctor");
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                String login = rs.getString("login");
                String password_doctor = rs.getString("password_doctor");
                int id_speciality = rs.getInt("id_speciality");

                Doctor doctor = new Doctor(first_name, middle_name, last_name, login, password_doctor, id_speciality, id);
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctorList;
    }

}
