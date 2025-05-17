package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

// DataSource to get a Connection handled by Hikari connection pool manager, better than DriverManager
public class HikariCPManager {
    public static HikariDataSource dataSource;
    public static HikariConfig config = null;

    HikariCPManager() {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/demo");
        config.setUsername("root");
        config.setPassword("password");
        // Set maximum connection pool size
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getPooledConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close () {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
