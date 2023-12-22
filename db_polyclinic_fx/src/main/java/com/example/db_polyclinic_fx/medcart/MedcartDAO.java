package com.example.db_polyclinic_fx.medcart;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.medcart.Medcart;

import java.sql.*;
import java.time.LocalDate;

public class MedcartDAO {
    private final DatabaseConnection connection;
    public MedcartDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    //пишу сущность, потому что Prescription писать долго)
    // Получение сущности по ID
    public Medcart getmedcartById(int id_medcard){
        String sql = "SELECT * FROM public.medcard WHERE id_medcard = ?";
        Medcart medcart = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_medcard);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                medcart = new Medcart(rs.getObject("date_create", LocalDate.class), rs.getInt("id_patient"), rs.getInt("id_medcard"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medcart;
    }

    // вывод всех сущностей
    public void getAllMedcarts() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.medcard")) {
            while (rs.next()) {
                int id_medcard = rs.getInt("id_medcard");
                LocalDate date_create = rs.getObject("date_create", LocalDate.class);
                int id_patient = rs.getInt("id_patient");

                // Делаем что-то с полученными данными, например, выводим их на экран
                System.out.println( "id_medcard = " + id_medcard +
                        ", date_create = " + date_create +
                        ", id_patient = " + id_patient
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Создание сущности
    public void createMedcart(Medcart medcart) {
        String sql = "INSERT INTO public.medcard (date_create, id_patient) VALUES (?, ?)";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, medcart.getDate_create());
            preparedStatement.setInt(2, medcart.getId_patient());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
