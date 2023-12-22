package com.example.db_polyclinic_fx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String url = "jdbc:postgresql://localhost:5432/Polyclinic";
    private final String user;
    private final String password;

    public DatabaseConnection(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
