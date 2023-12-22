package com.example.db_polyclinic_fx;

import com.example.db_polyclinic_fx.Oms.Oms;
import com.example.db_polyclinic_fx.Oms.OmsDAO;
import com.example.db_polyclinic_fx.Patient.Patient;
import com.example.db_polyclinic_fx.Patient.PatientDAO;
import com.example.db_polyclinic_fx.Patient.PatientTable;
import com.example.db_polyclinic_fx.doctor.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HelloController {

    private static String login;
    private static String password;
    private static DatabaseConnection connection;

    @FXML
    private VBox root;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;
    @FXML
    private Button profileButton;

    public void onLoginButtonClick(ActionEvent actionEvent) {
        login = loginField.getText();
        password = passwordField.getText();

        // Подключиться к базе данных
        connection = new DatabaseConnection(login, password);
        try {
            connection.connect();
            System.out.println("OK");
            createMainScene();


        } catch (SQLException throwables) {
            Label errorLabel = new Label("Неверный логин или пароль");
            errorLabel.setStyle("-fx-text-fill: red;");
            root.getChildren().add(errorLabel);
            throwables.printStackTrace();

        }

    }

    public void createMainScene() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
            Parent root = null;
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 640, 480));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleHomeButtonClick(ActionEvent actionEvent) {
    }

    public void handleProfileButtonClick(ActionEvent actionEvent) {
        System.out.println("onProfileClick!");
        // Создать новое окно
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Profile");

        // Создать корневой контейнер
        VBox root = new VBox();
        root.setSpacing(10);

        System.out.println(login);
        DoctorDAO doctorDAO = new DoctorDAO(connection);
        Doctor doctor = doctorDAO.getDoctorByLogin(login);

        // Создать лейблы
        Label nameLabel = new Label("ФИО:");
        Label name = new Label(doctor.getFirst_name() + " " + doctor.getMiddle_name() + " " + doctor.getLast_name());
        Label specLabel = new Label("Специальность:");

        SpecialityDAO specialityDAO = new SpecialityDAO(connection);
        Speciality speciality_db = specialityDAO.getSpecById(doctor.getId_speciality());
        Label speciality = new Label(speciality_db.getName_speciality());


        // Добавить лейблы и поля в корневой контейнер
        root.getChildren().addAll(nameLabel,name, specLabel, speciality);

        // Создать кнопку OK
        Button okBtn = new Button("OK");
        okBtn.setOnAction(event -> {
            // Закрыть окно
            dialogStage.close();
        });

        root.getChildren().add(okBtn);
        Scene scene = new Scene(root, 640, 200);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void handlePatientsButtonClick(ActionEvent actionEvent) {
        System.out.println("onPatientsButtonClick!");
        // Создать новое окно
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Patient");

        // Создать корневой контейнер
        VBox root = new VBox();
        root.setSpacing(10);

        TableView patientTable = new TableView();


        TableColumn<String, String> firstNameColumn = new TableColumn<>("First_Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));

        TableColumn<String, String> midNameColumn = new TableColumn<>("Middle_name");
        midNameColumn.setCellValueFactory(new PropertyValueFactory<>("middle_name"));

        TableColumn<String, String> lastNameColumn = new TableColumn<>("Last_name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        TableColumn<String, String> birthColumn = new TableColumn<>("Date_birth");
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("date_birth"));

        TableColumn<String, String> snilsColumn = new TableColumn<>("Snils");
        snilsColumn.setCellValueFactory(new PropertyValueFactory<>("snils"));

        TableColumn<String, String> phoneColumn = new TableColumn<>("Phone_number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));

        TableColumn<String, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<String, String> omsColumn = new TableColumn<>("Oms");
        omsColumn.setCellValueFactory(new PropertyValueFactory<>("oms"));


        patientTable.getColumns().addAll(firstNameColumn, midNameColumn, lastNameColumn, birthColumn, snilsColumn, phoneColumn, genderColumn, omsColumn);
        root.getChildren().add(patientTable);

//         Получить список сущностей
        PatientDAO patientDAO = new PatientDAO(connection);
        List<Patient> patients = patientDAO.getAllPatientss();
        System.out.println("Пациенты" + patientDAO.getAllPatientss());

        PatientTable p;
        OmsDAO omsDAO = new OmsDAO(connection);
        Oms oms;

        // Добавить сущности в таблицу
        for (Patient patient  : patients) {
            oms = omsDAO.getOmsById(patient.getId_oms());
            p = new PatientTable(patient.getFirst_name(), patient.getMiddle_name(), patient.getLast_name(), patient.getDate_birth() + "", patient.getSnils(), patient.getPhone_number(), patient.getGender(), oms.toString());
            patientTable.getItems().add(p);
        }

        Scene scene = new Scene(root, 640, 200);
        dialogStage.setScene(scene);
        dialogStage.show();

    }

    public void handleDoctorsButtonClick(ActionEvent actionEvent) {
        System.out.println("onDoctorsButtonClick!");
        // Создать новое окно
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Doctors");

        // Создать корневой контейнер
        VBox root = new VBox();
        root.setSpacing(10);

        TableView doctorTable = new TableView();

        // Создать столбцы в таблице
        TableColumn<String, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<String, String> firstNameColumn = new TableColumn<>("First_Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));

        TableColumn<String, String> midNameColumn = new TableColumn<>("Middle_name");
        midNameColumn.setCellValueFactory(new PropertyValueFactory<>("middle_name"));

        TableColumn<String, String> lastNameColumn = new TableColumn<>("Last_name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        TableColumn<String, String> specColumn = new TableColumn<>("Speciality");
        specColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

        doctorTable.getColumns().addAll(idColumn, firstNameColumn, midNameColumn, lastNameColumn, specColumn);

        root.getChildren().add(doctorTable);

//         Получить список сущностей
        DoctorDAO doctorDAO = new DoctorDAO(connection);
        List<Doctor> doctors = doctorDAO.getAllDoctors();

        SpecialityDAO specialityDAO = new SpecialityDAO(connection);
        Speciality speciality;
        DoctorTable d;

        // Добавить сущности в таблицу
        for (Doctor doctor : doctors) {
            speciality = specialityDAO.getSpecById(doctor.getId_speciality());
            d = new DoctorTable(doctor.getId_doctor(), doctor.getFirst_name(), doctor.getMiddle_name(), doctor.getLast_name(), speciality.getName_speciality());
            doctorTable.getItems().add(d);
        }

        Scene scene = new Scene(root, 640, 200);
        dialogStage.setScene(scene);
        dialogStage.show();
    }


}