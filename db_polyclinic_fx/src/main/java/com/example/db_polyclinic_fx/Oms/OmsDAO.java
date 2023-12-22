package com.example.db_polyclinic_fx.Oms;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.Oms.Oms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OmsDAO {

    private final DatabaseConnection connection;

    public OmsDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Метод для получения сущности по ID
    public Oms getOmsById(int id_oms) {
        String sql = "SELECT * FROM public.oms WHERE id_oms = ?";
        Oms oms = null;

        try (Connection conn = connection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_oms);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                oms = new Oms(rs.getInt("id_oms"), rs.getString("seria"),rs.getString("number_oms"), rs.getString("organization_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oms;
    }
}
