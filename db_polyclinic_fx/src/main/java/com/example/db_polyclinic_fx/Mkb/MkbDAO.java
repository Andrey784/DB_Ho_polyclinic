package com.example.db_polyclinic_fx.Mkb;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.Record.Record_db;
import com.example.db_polyclinic_fx.doctor.Doctor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MkbDAO {
    private final DatabaseConnection connection;
    public MkbDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Получение сущности по названию
    public Mkb getMkbByName(String name_diagnosis){
        String sql = "SELECT * FROM public.mkb_10 WHERE name_diagnosis = ?";
        Mkb mkb = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name_diagnosis);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                mkb = new Mkb(rs.getString("code_diagnosis"), name_diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mkb;
    }

    //Получение всех имен сущностей
    public List<String> getAllNames() {
        List<String> mkbList = new ArrayList<>();

        try (Connection conn = connection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM public.mkb_10")) {

            while (rs.next()) {
                String name_diagnosis = rs.getString("name_diagnosis");
                mkbList.add(name_diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mkbList;
    }

    // вывод всех сущностей
    public void getAllMkb() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.mkb_10")) {
            while (rs.next()) {
                String code_diagnosis = rs.getString("code_diagnosis");
                String name_diagnosis = rs.getString("name_diagnosis");

                System.out.println( "code_diagnosis = " + code_diagnosis +
                        ", name_diagnosis = " + name_diagnosis
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
