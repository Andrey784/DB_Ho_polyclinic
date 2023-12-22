package com.example.db_polyclinic_fx.Patient;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.Patient.Patient;
import com.example.db_polyclinic_fx.Record.Record_db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private final DatabaseConnection connection;
    public PatientDAO(DatabaseConnection connection) {
        this.connection = connection;
    }
    // вывод всех сущностей
//    public void getAllPatients() {
//        try (Connection conn = connection.connect();
//             Statement statement = conn.createStatement();
//             ResultSet rs = statement.executeQuery("SELECT * FROM public.patient")) {
//            while (rs.next()) {
//                int id_patient = rs.getInt("id_patient");
//                String first_name = rs.getString("first_name");
//                String middle_name = rs.getString("middle_name");
//                String last_name = rs.getString("last_name");
//                LocalDate date_birth = rs.getObject("date_birth", LocalDate.class);
//                String snils = rs.getString("snils");
//                String phone_number = rs.getString("phone_number");
//                String gender = rs.getString("gender");
//                int id_oms = rs.getInt("id_oms");
//
//                System.out.println("id_patient = " + id_patient +
//                        ", first_name = " + first_name +
//                        ", middle_name = " + middle_name +
//                        ", last_name = " + last_name +
//                        ", date_birth = " + date_birth +
//                        ", snils = " + snils +
//                        ", phone_number = " + phone_number +
//                        ", id_oms = " + id_oms +
//                        ", gender = " + gender
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Patient getPatientByPhone(String phone_number){
        String sql = "SELECT * FROM public.patient WHERE phone_number = ?";
        Patient patient = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, phone_number);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int id_patient = rs.getInt("id_patient");
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                LocalDate date_birth = rs.getObject("date_birth", LocalDate.class);
                String snils = rs.getString("snils");
                String gender = rs.getString("gender");
                int id_oms = rs.getInt("id_oms");
                patient  = new Patient(first_name, middle_name, last_name, date_birth, snils,phone_number, gender, id_oms, id_patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public List<Patient> getAllPatientss() {
        List<Patient> patientList = new ArrayList<>();

        try (Connection conn = connection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM public.patient")) {

            while (rs.next()) {
                String first_name = rs.getString("first_name");
                String middle_name = rs.getString("middle_name");
                String last_name = rs.getString("last_name");
                LocalDate date_birth = rs.getObject("date_birth", LocalDate.class);
                String snils = rs.getString("snils");
                String phone_number = rs.getString("phone_number");
                String gender = rs.getString("gender");
                int id_oms = rs.getInt("id_oms");

                // Создаем экземпляр Entity и добавляем его в список
                Patient patient = new Patient(first_name, middle_name, last_name, date_birth, snils, phone_number, gender, id_oms);
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientList;
    }

}
