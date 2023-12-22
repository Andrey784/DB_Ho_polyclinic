package com.example.db_polyclinic_fx.doctor;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.doctor.Speciality;

import java.sql.*;

public class SpecialityDAO {
    private final DatabaseConnection connection;
    public SpecialityDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Получение сущности по ID
    public Speciality getSpecById(int id_speciality){
        String sql = "SELECT * FROM public.speciality_doctor WHERE id_speciality = ?";
        Speciality speciality = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_speciality);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                speciality = new Speciality(rs.getInt("id_speciality"), rs.getString("name_speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return speciality;
    }

    // вывод всех сущностей
    public void getAllSpeciality() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.speciality_doctor")) {
            while (rs.next()) {
                int id_speciality = rs.getInt("id_speciality");
                String name_speciality = rs.getString("name_speciality");

                System.out.println("id_speciality = " + id_speciality +
                        ", name_speciality = " + name_speciality
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
