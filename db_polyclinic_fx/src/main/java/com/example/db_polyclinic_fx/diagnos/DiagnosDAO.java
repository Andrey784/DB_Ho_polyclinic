package com.example.db_polyclinic_fx.diagnos;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.diagnos.Diagnos;

import java.sql.*;

public class DiagnosDAO {
    private final DatabaseConnection connection;
    public DiagnosDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Получение сущности по ID
    public Diagnos getDiagnosisById(int id_record){
        String sql = "SELECT * FROM public.diagnosis WHERE id_record = ?";
        Diagnos diagnos = null;
        try  (Connection conn = connection.connect();
              PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_record);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                diagnos = new Diagnos(rs.getBoolean("is_first"), rs.getString("code_diagnosis"), rs.getInt("id_record"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diagnos;
    }

    // вывод всех сущностей
    public void getAllDiagnosis() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.diagnosis")) {
            while (rs.next()) {
                Boolean is_first = rs.getBoolean("is_first");
                String code_diagnosis = rs.getString("code_diagnosis");
                int id_record = rs.getInt("id_record");

                System.out.println( "is_first = " + is_first +
                        ", code_diagnosis = " + code_diagnosis +
                        ", id_record = " + id_record
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Создание сущности
    public void createDiagnosis(Diagnos diagnos) {
        String sql = "INSERT INTO public.diagnosis (is_first, code_diagnosis) VALUES (?, ?)";

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, diagnos.getIs_first());
            preparedStatement.setString(2, diagnos.getCode_diagnosis());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для обновления сущности
    public void updateDiagnosis(Diagnos diagnos) {
        String sql = "UPDATE public.diagnosis SET is_first = ?, code_diagnosis = ? WHERE id_record = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, diagnos.getIs_first());
            preparedStatement.setString(2, diagnos.getCode_diagnosis());
            preparedStatement.setInt(3, diagnos.getId_record());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления сущности по ID
    public void deleteDiagnosisById(int id_record) {
        String sql = "DELETE FROM public.diagnosis WHERE id_record = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_record);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
