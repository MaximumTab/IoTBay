package com.iotbay.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector implements AutoCloseable {
    private Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBConnector() {
        String os = System.getProperty("os.name").toLowerCase();
        String path = "C:/Users/kevin/OneDrive/Documents/software development github/IoTBay/.smarttomcat/IOTBayWebsite/IotBay.db";
        String url = "jdbc:sqlite:" + path;
        System.out.println("Attempting to connect to DB at: " + url);

        try {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
