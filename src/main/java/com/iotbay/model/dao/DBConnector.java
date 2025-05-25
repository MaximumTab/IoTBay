package com.iotbay.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    private Connection connection;

    public DBConnector() {
        System.setProperty("org.sqlite.lib.verbose", "true");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:IotBay.db";

        try {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);
            System.out.println("Connected to database");

            // Staff

            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS Staff (" +
                                "  staff_id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "  name       TEXT    NOT NULL," +
                                "  email      TEXT    NOT NULL UNIQUE," +
                                "  position   TEXT    NOT NULL," +
                                "  address    TEXT    NOT NULL," +
                                "  status     TEXT    NOT NULL CHECK(status IN ('ACTIVE','INACTIVE'))" +
                                ");"
                );
                System.out.println("Ensured Staff table exists");
            } catch (SQLException e) {
                System.err.println("Failed to create Staff table:");
                e.printStackTrace();
            }

            // Suppliers
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS Suppliers (" +
                                "  supplier_id   INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "  name          TEXT    NOT NULL," +
                                "  email         TEXT," +
                                "  type          TEXT    CHECK(type IN ('company','individual'))," +
                                "  address       TEXT," +
                                "  is_active     INTEGER DEFAULT 1 CHECK(is_active IN (0,1))" +
                                ");"
                );
                System.out.println("Ensured Suppliers table exists");
            } catch (SQLException e) {
                System.err.println("Failed to create Suppliers table:");
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
