package com.iotbay.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        //String url = "jdbc:sqlite:C:/Users/paken/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db";


        //String url = "jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db";
        try {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);
            System.out.println("Connected to: " + url);
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
