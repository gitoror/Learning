package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connection is the most basic object to connect to a db
// DriverManager to get a Connection
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/demo";
    private final String user = "root";
    private final String password = "password";

    private DatabaseConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
