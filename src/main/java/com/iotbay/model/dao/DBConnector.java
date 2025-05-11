package com.iotbay.model.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");          // registers the driver
            System.out.println("SQLite driver loaded");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();                       // will print if the jar still isn’t on the class‑path
        }
    }

    public DBConnector() {

        System.setProperty("org.sqlite.lib.verbose", "true");
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String os = System.getProperty("os.name").toLowerCase();
        String url;
        if (os.contains("mac")) {
            url = "jdbc:sqlite=/Users/joelormerod/.SmartTomcat/smarttomcat/IoTBay/IoTBay/IotBay.db";
        } else {
            url = "jdbc:sqlite=C:/Users/yourWindowsUser/.smarttomcat/IoTBay/IotBay.db";
        }


        try {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);
            System.out.println("Connected to database");
        }
        catch (SQLException e) {
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}