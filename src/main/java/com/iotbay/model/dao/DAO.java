package com.iotbay.model.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    ArrayList<DBManager<?>> tables;
    Connection connection;

    public DAO() throws SQLException {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try {
            tables.add(new DevicesDBManager(connection));
            tables.add(new UserDBManager(connection));
            tables.add(new CreditCardsDBManager(connection));
            //tables.add(new AccessLogDBManager(connection));
            //tables.add(new OrderDBManager(connection));
            //tables.add(new OrderItemDBManager(connection));
            //tables.add(new PaymentDBManager(connection));
            //tables.add(new StaffDetail(connection));
            //tables.add(new SupplierDBManager(connection));

        } catch (SQLException ex) {
            System.out.println("Error initializing DBManagers");
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public CreditCardsDBManager CreditCards() 
    {
        return (CreditCardsDBManager) tables.get(2);
    }

    public DevicesDBManager Devices()
    {
        return (DevicesDBManager) tables.get(0);
    }

    public UserDBManager Users()
    {
        return (UserDBManager) tables.get(1);
    }

}

