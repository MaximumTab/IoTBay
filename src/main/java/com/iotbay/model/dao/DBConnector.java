package com.iotbay.model.dao;

import java.sql.*;

public class DBConnector
{
    private Connection con;

    public DBConnector() {
        System.setProperty("org.sqlite.lib.verbose", "true");

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:sqlite:IOTBayDB.sqlite";
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            System.out.println("Connected to the database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return con;
    }

    public void closeConnection()
    {
        try
        {
            if (con != null)
            {
                con.close();
                System.out.println("Connection closed successfully");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
