package com.example.db_polyclinic_fx.drug;

import com.example.db_polyclinic_fx.DatabaseConnection;
import com.example.db_polyclinic_fx.drug.DrugPrescription;

import java.sql.*;
import java.util.ArrayList;

public class DrugPrescriptionDAO {
    private final DatabaseConnection connection;

    public DrugPrescriptionDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    // Метод для получения сущности по ID
    public ArrayList<DrugPrescription> getDrugPrescriptionByIdDrug(int id_drug) {
        String sql = "SELECT id_drug, id_prescription FROM public.drug_prescription WHERE id_drug = ?";
        ArrayList<DrugPrescription> drugPrescriptions = new ArrayList<DrugPrescription>();

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_drug);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                drugPrescriptions.add(new DrugPrescription(resultSet.getInt("id_drug"), resultSet.getInt("id_prescription")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return drugPrescriptions;
    }

    // получение всех сущностей
    public void getAllDrugPrescriptions() {
        try (Connection conn = connection.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM public.drug_prescription")) {
            while (resultSet.next()) {
                int id_drug = resultSet.getInt("id_drug");
                int id_prescription = resultSet.getInt("id_prescription");

                System.out.println("id_drug: " + id_drug + ", id_prescription: " + id_prescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //создание сущности
    public void createDrugPrescription(DrugPrescription drugPrescription) {
        String sql = "INSERT INTO public.drug_prescription (id_drug, id_prescription) VALUES (?, ?)";

        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, drugPrescription.getId_drug());
            preparedStatement.setInt(2, drugPrescription.getId_prescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Метод для обновления сущности
    public void updateDrugPrescriptions(DrugPrescription drugPrescription) {
        String sql = "UPDATE public.drug_prescription SET id_drug = ? WHERE id_prescription = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1,drugPrescription.getId_drug());
            preparedStatement.setInt(2, drugPrescription.getId_prescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления сущности по ID
    public void deleteDrugPrescription(DrugPrescription drugPrescription) {
        String sql = "DELETE FROM public.drug_prescription WHERE id_drug = ? AND id_prescription = ?";
        try (Connection conn = connection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, drugPrescription.getId_drug());
            preparedStatement.setInt(2, drugPrescription.getId_prescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
