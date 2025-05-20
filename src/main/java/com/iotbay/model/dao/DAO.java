package com.iotbay.model.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO
{
    ArrayList<DBManager<?>> tables;
    Connection connection;

    public DAO() throws SQLException
    {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try
        {
            tables.add(new UserDBManager(connection));
            tables.add(new CreditCardsDBManager(connection));
            tables.add(new PaymentHistoryDBManager(connection));
        }
        catch (SQLException ex)
        {
            System.out.println("Error initializing DBManagers");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public UserDBManager Users() {
        return (UserDBManager) tables.get(0);
    }

    public CreditCardsDBManager CreditCards() {
        return (CreditCardsDBManager) tables.get(1);
    }

    public PaymentHistoryDBManager PaymentHistory() {return (PaymentHistoryDBManager) tables.get(2);}

}

