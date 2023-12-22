module com.example.db_polyclinic_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.db_polyclinic_fx to javafx.fxml;
    exports com.example.db_polyclinic_fx;
    exports com.example.db_polyclinic_fx.doctor;
    opens com.example.db_polyclinic_fx.doctor to javafx.fxml;
    exports com.example.db_polyclinic_fx.diagnos;
    opens com.example.db_polyclinic_fx.diagnos to javafx.fxml;
    exports com.example.db_polyclinic_fx.drug;
    opens com.example.db_polyclinic_fx.drug to javafx.fxml;
    exports com.example.db_polyclinic_fx.medcart;
    opens com.example.db_polyclinic_fx.medcart to javafx.fxml;
    exports com.example.db_polyclinic_fx.Mkb;
    opens com.example.db_polyclinic_fx.Mkb to javafx.fxml;
    exports com.example.db_polyclinic_fx.Oms;
    opens com.example.db_polyclinic_fx.Oms to javafx.fxml;
    exports com.example.db_polyclinic_fx.Patient;
    opens com.example.db_polyclinic_fx.Patient to javafx.fxml;
    exports com.example.db_polyclinic_fx.Record;
    opens com.example.db_polyclinic_fx.Record to javafx.fxml;
}