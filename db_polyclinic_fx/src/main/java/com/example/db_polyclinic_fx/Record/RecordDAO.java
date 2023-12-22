package com.example.db_polyclinic_fx.Record;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.Record.Record_db;

import java.sql.*;
import java.time.LocalDate;

public class RecordDAO {
    private final DatabaseConnection connection;
    public RecordDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    //пишу сущность, потому что Prescription писать долго)
    // Получение сущности по ID
    public Record_db getRecordById(int id_record){
        String sql = "SELECT * FROM public.record WHERE id_record = ?";
        Record_db record = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_record);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                record  = new Record_db(rs.getObject("date_record", LocalDate.class), rs.getString("complaints"), rs.getInt("id_medcard"), rs.getInt("id_doctor"), rs.getInt("id_record"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }
    // вывод всех сущностей
    public void getAllRecords() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.record")) {
            while (rs.next()) {
                int id_record = rs.getInt("id_record");
                LocalDate date_record = rs.getObject("date_record", LocalDate.class);
                String complaints = rs.getString("complaints");
                int id_medcard = rs.getInt("id_medcard");
                int id_doctor = rs.getInt("id_doctor");

                System.out.println( "id_record = " + id_record +
                        ", date_record = " + date_record +
                        ", complaints = " + complaints +
                        ", id_medcard = " + id_medcard +
                        ", id_doctor = " + id_doctor
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Создание сущности
    public void createRecord(Record_db record) {
        String sql = "INSERT INTO public.record (date_record, complaints, id_medcard, id_doctor) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, record.getDate_record());
            preparedStatement.setString(2, record.getComplaints());
            preparedStatement.setInt(3, record.getId_medcard());
            preparedStatement.setInt(4, record.getId_doctor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для обновления сущности
    public void updateRecord(Record_db record) {
        String sql = "UPDATE public.record SET date_record = ?, complaints = ?, id_medcard = ?, id_doctor = ?  WHERE id_record = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1,record.getDate_record());
            preparedStatement.setString(2, record.getComplaints());
            preparedStatement.setInt(3, record.getId_medcard());
            preparedStatement.setInt(4, record.getId_doctor());
            preparedStatement.setInt(5, record.getId_record());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Метод для удаления сущности по ID
    public void deleteRecordById(int id_record) {
        String sql = "DELETE FROM public.record WHERE id_record = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_record);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


