package com.example.db_polyclinic_fx.drug;

import com.example.db_polyclinic_fx.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;

public class PrescriptionDAO {
    private final DatabaseConnection connection;
    public PrescriptionDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    //пишу сущность, потому что Prescription писать долго)
    // Получение сущности по ID
    public Prescription getPrescriptionById(int prescriptionId){
        String sql = "SELECT * FROM public.prescription WHERE id_prescription = ?";
        Prescription prescription = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, prescriptionId);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                prescription = new Prescription(rs.getObject("date_prescription", LocalDate.class), rs.getInt("period"), rs.getInt("id_record"), rs.getInt("id_prescription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescription;
    }


    // вывод всех сущностей
    public void getAllPrescriptions() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.prescription")) {
            while (rs.next()) {
                int id_prescription = rs.getInt("id_prescription");
                LocalDate date_prescription = rs.getObject("date_prescription", LocalDate.class);
                int period = rs.getInt("period");
                int id_record = rs.getInt("id_record");

                // Делаем что-то с полученными данными, например, выводим их на экран
                System.out.println("id_prescription = " + id_prescription +
                        ", date_prescription = " + date_prescription +
                        ", period = " + period +
                        ", id_record = " + id_record
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Создание сущности
    public void createPrescription(Prescription prescription) {
        String sql = "INSERT INTO public.prescription (date_prescription, period, id_record) VALUES (?, ?, ?)";

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, prescription.getDate_prescription());
            preparedStatement.setInt(2, prescription.getPeriod());
            preparedStatement.setInt(3, prescription.getId_record());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для обновления сущности
    public void updatePrescription(Prescription prescription) {
        String sql = "UPDATE public.prescription SET date_prescription = ?, period = ?, id_record = ? WHERE id_prescription = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1,prescription.getDate_prescription());
            preparedStatement.setInt(2, prescription.getPeriod());
            preparedStatement.setInt(3, prescription.getId_record());
            preparedStatement.setInt(4, prescription.getId_prescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Можно реализовать, если придумать как нормально определять тип поля
//    // Метод для обновления единичного поля сущности
//    public void updatePrescriptionField(int id_prescription, String fieldName, String value) {
//        String sql = "UPDATE public.items SET " + fieldName + " = ? WHERE id_prescription = ?";
//
//        try (Connection conn = connection.connect();
//             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//            preparedStatement.setString(1, value);
//            preparedStatement.setInt(2, id_prescription);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // Метод для удаления сущности по ID
    public void deletePrescriptionById(int id_prescription) {
        String sql = "DELETE FROM public.prescription WHERE id_prescription = ?";

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_prescription);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
