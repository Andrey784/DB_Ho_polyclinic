package com.example.db_polyclinic_fx.Mkb;

import com.example.db_polyclinic_fx.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MkbDAO {
    private final DatabaseConnection connection;
    public MkbDAO(DatabaseConnection connection) {
        this.connection = connection;
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
