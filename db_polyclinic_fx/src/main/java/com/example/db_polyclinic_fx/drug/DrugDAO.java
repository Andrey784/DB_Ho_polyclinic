package com.example.db_polyclinic_fx.drug;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.drug.Drug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDAO {

    private final DatabaseConnection connection;
    public DrugDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Получение сущности по ID
    public Drug getDrugById(int id_drug){
        String sql = "SELECT * FROM public.drug WHERE id_drug = ?";
        Drug drug = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_drug);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                drug = new Drug(rs.getInt("id_drug"), rs.getString("name_drug"), rs.getString("dosage"), rs.getString("release_form"), rs.getString("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drug;
    }
    //Получение всех имен сущностей
    public List<String> getAllNames() {
        List<String> drugList = new ArrayList<>();

        try (Connection conn = connection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM public.drug")) {

            while (rs.next()) {
                String name_drug = rs.getString("name_drug");
                drugList.add(name_drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugList;
    }


    // вывод всех сущностей
    public void getAllDrugs() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.drug")) {
            while (rs.next()) {
                int id_drug = rs.getInt("id_drug");
                String name_drug = rs.getString("name_drug");
                String dosage = rs.getString("dosage");
                String release_form = rs.getString("release_form");
                String quantity = rs.getString("quantity");


                System.out.println("id_drug=" + id_drug +
                        ", name_drug='" + name_drug + '\''
                        +", dosage='" + dosage + '\''
                        +", release_form='" + release_form + '\'' +
                        ", quantity='" + quantity + '\''
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
