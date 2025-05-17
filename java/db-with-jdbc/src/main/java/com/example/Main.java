package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.*;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        getAloWithDatabaseConnection();
        getAloWithHikariCPManager();
    }

    public static void getAloWithDatabaseConnection() {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM alo;");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("nom"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAloWithHikariCPManager() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            HikariCPManager hcpm = new HikariCPManager();
            conn = hcpm.getPooledConnection();
            if (conn != null) {
                pstmt = conn.prepareStatement("SELECT * FROM alo");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("Nom: " + rs.getString("nom"));
                }
            } else {
                System.out.println("Error getting connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        HikariCPManager.close();
    }
}
