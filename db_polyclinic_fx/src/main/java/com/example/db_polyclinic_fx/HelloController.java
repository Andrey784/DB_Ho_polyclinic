package com.example.db_polyclinic_fx;

import com.example.db_polyclinic_fx.Mkb.Mkb;
import com.example.db_polyclinic_fx.Mkb.MkbDAO;
import com.example.db_polyclinic_fx.Oms.Oms;
import com.example.db_polyclinic_fx.Oms.OmsDAO;
import com.example.db_polyclinic_fx.Patient.Patient;
import com.example.db_polyclinic_fx.Patient.PatientDAO;
import com.example.db_polyclinic_fx.Patient.PatientTable;
import com.example.db_polyclinic_fx.Record.RecordDAO;
import com.example.db_polyclinic_fx.Record.RecordTable;
import com.example.db_polyclinic_fx.Record.Record_db;
import com.example.db_polyclinic_fx.doctor.*;
import com.example.db_polyclinic_fx.drug.DrugDAO;
import com.example.db_polyclinic_fx.drug.Prescription;
import com.example.db_polyclinic_fx.medcart.MedcartDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.LocalDate;
import java.util.List;

public class HelloController {

    private static String login;
    private static String password;
    private static DatabaseConnection connection;
    private static TableView patientTable;

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
        root.getChildren().addAll(nameLabel, name, specLabel, speciality);

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

        patientTable = new TableView();


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
        for (Patient patient : patients) {
            oms = omsDAO.getOmsById(patient.getId_oms());
            p = new PatientTable(patient.getFirst_name(), patient.getMiddle_name(), patient.getLast_name(), patient.getDate_birth() + "", patient.getSnils(), patient.getPhone_number(), patient.getGender(), oms.toString());
            patientTable.getItems().add(p);
        }

        Scene scene = new Scene(root, 640, 200);
        dialogStage.setScene(scene);
        dialogStage.show();

        addMenu();

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

    public void addMenu() {

        // Создать объект типа ContextMenu
        ContextMenu contextMenu = new ContextMenu();

        // Добавить элемент в контекстное меню
        MenuItem showMedcard = new MenuItem("Медкарта");
        showMedcard.setOnAction(event -> {
            PatientDAO patientDAO = new PatientDAO(connection);
            PatientTable patientT = (PatientTable) patientTable.getSelectionModel().getSelectedItem();
            MedcartDAO medcartDAO = new MedcartDAO(connection);
            Patient patient = patientDAO.getPatientByPhone(patientT.getPhone_number());
            System.out.println("Patient = " + patient);
            int id_medcard = medcartDAO.getmedcartByPatientId(patient.getId_patient()).getId_medcard();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Medcard");

            VBox root = new VBox();
            root.setSpacing(10);

            TableView recordTable = new TableView();

            // Создать столбцы в таблице
            TableColumn<String, String> dateColumn = new TableColumn<>("Date_record");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_record"));

            TableColumn<String, String> complaintsColumn = new TableColumn<>("Complaints");
            complaintsColumn.setCellValueFactory(new PropertyValueFactory<>("complaints"));

            TableColumn<String, String> medcardColumn = new TableColumn<>("Id_medcard");
            medcardColumn.setCellValueFactory(new PropertyValueFactory<>("id_medcard"));

            TableColumn<String, String> doctorColumn = new TableColumn<>("Doctor");
            doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));

            recordTable.getColumns().addAll(dateColumn, complaintsColumn, medcardColumn, doctorColumn);

            root.getChildren().add(recordTable);

            RecordDAO recordDAO = new RecordDAO(connection);
            List<Record_db> recordDbList = recordDAO.getAllRecordsByMedCardId(id_medcard);

            RecordTable r;
            DoctorDAO doctorDAO = new DoctorDAO(connection);
            Doctor doctor;

            for (Record_db recordDb : recordDbList) {
                doctor = doctorDAO.getDoctorById(recordDb.getId_doctor());
                r = new RecordTable(recordDb.getDate_record() + "", recordDb.getComplaints(), recordDb.getId_medcard() + "", doctor.getFirst_name() + " " + doctor.getMiddle_name() + " " + doctor.getLast_name());
                recordTable.getItems().add(r);
            }

            // Создать кнопку создания record в медкарте
            Button addButton = new Button("Добавить");
            addButton.setOnAction(event1 -> {
                System.out.println("addBtn!");
                // Создать новое окно
                Stage dialogStageAdd = new Stage();
                dialogStageAdd.initModality(Modality.APPLICATION_MODAL);
                dialogStageAdd.setTitle("Add Record");

                // Создать корневой контейнер
                VBox rootAdd = new VBox();
                rootAdd.setSpacing(10);

                LocalDate today = LocalDate.now();
                String patientName = patient.getFirst_name() + " " + patient.getMiddle_name() + " " + patient.getLast_name() + " " + patient.getDate_birth();
                DoctorDAO doctorDAOnow = new DoctorDAO(connection);
                Doctor doctorNow = doctorDAOnow.getDoctorByLogin(login);
                String doctorName = doctorNow.getFirst_name() + " " + doctorNow.getMiddle_name() + " " + doctorNow.getLast_name();
                SpecialityDAO specialityDAO = new SpecialityDAO(connection);
                Speciality speciality = specialityDAO.getSpecById(doctorNow.getId_speciality());

                //Создание лейблов
                Label daterecordLabel = new Label("Дата: " + today);
                Label namePatientLabel = new Label("Пациент: " + patientName);
                Label doctorLabel = new Label("Врач " + speciality.getName_speciality() + ": " + doctorName);

                Label complaintsLabel = new Label("Жалобы: ");
                TextField complaintsTF = new TextField();


                MkbDAO mkbDAO = new MkbDAO(connection);
                List<String> mkbList = mkbDAO.getAllNames();

                Label mkbLabel = new Label("Диагноз: ");
                ComboBox<String> mkbComboBox = new ComboBox<>();
                ObservableList<String> diagnosisOptions = FXCollections.observableArrayList(
                        mkbList
                );
                mkbComboBox.setItems(diagnosisOptions);
                mkbComboBox.setOnAction(event2 -> {
                    String selectedDiagnosis = mkbComboBox.getValue();
                    System.out.println("Selected Diagnos = " + selectedDiagnosis);
                });

                Label firstLabel = new Label("Впервые: ");
                ComboBox<String> firstComboBox = new ComboBox<>();
                ObservableList<String> firstOptions = FXCollections.observableArrayList(
                        "Да",
                        "Нет"
                );
                firstComboBox.setItems(firstOptions);
                firstComboBox.setOnAction(event3 -> {
                    String firstRes = firstComboBox.getValue();
                    System.out.println("First = " + firstRes);
                });


                DrugDAO drugDAO = new DrugDAO(connection);
                List<String> drugsList = drugDAO.getAllNames();
                Label prescriptionLabel = new Label("Назначение: ");
                ComboBox<String> prescriptionComboBox = new ComboBox<>();
                ObservableList<String> prescriptionOptions = FXCollections.observableArrayList(
                        drugsList
                );
                prescriptionComboBox.setItems(prescriptionOptions);
                prescriptionComboBox.setOnAction(event4 -> {
                    String prescriptionRes = prescriptionComboBox.getValue();
                    System.out.println("Drug = " + prescriptionRes);
                });

                Label periodLabel = new Label("Период: ");
                ComboBox<Integer> periodComboBox = new ComboBox<>();
                ObservableList<Integer> periodOptions = FXCollections.observableArrayList(
                  1, 2, 3, 4, 5, 6, 7, 8, 9
                );
                periodComboBox.setItems(periodOptions);
                periodComboBox.setOnAction(event5 -> {
                    Integer periodRes = periodComboBox.getValue();
                    System.out.println("period = " + periodRes);
                });


                // Добавить лейблы и поля в корневой контейнер
                rootAdd.getChildren().addAll(daterecordLabel, namePatientLabel, doctorLabel, complaintsLabel, complaintsTF, mkbLabel,mkbComboBox, firstLabel, firstComboBox, prescriptionLabel, prescriptionComboBox, periodLabel, periodComboBox);

                Button saveBtn = new Button("Сохранить");
                saveBtn.setOnAction(event5 -> {
                    System.out.println("SaveBtn");

                    Record_db recordDbNew = new Record_db(today, complaintsTF.getText().toString(), id_medcard, doctorNow.getId_doctor());
                    System.out.println("New record = " + recordDbNew);
                    recordDAO.createRecord(recordDbNew);


                    dialogStageAdd.close();
                    dialogStage.close();
                });

                rootAdd.getChildren().add(saveBtn);


                Scene scene = new Scene(rootAdd, 300, 400);
                dialogStageAdd.setScene(scene);

                dialogStageAdd.show();


            });

            root.getChildren().add(addButton);


            Scene scene = new Scene(root, 640, 200);
            dialogStage.setScene(scene);

            dialogStage.show();
        });
        contextMenu.getItems().add(showMedcard);
        patientTable.setContextMenu(contextMenu);


    }


}