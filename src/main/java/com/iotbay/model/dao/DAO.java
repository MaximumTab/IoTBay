package com.iotbay.model.dao;

<<<<<<< HEAD
import com.iotbay.model.AccessLog;
import com.iotbay.model.OrderItem;
import com.iotbay.model.StaffDetail;
import com.iotbay.model.User;
=======
>>>>>>> a21e133 (Initial commit from IntelliJ)

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

<<<<<<< HEAD
public class DAO
{
    ArrayList<DBManager<?>> tables;

    public DAO() throws SQLException
    {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try
        {
            tables.add(new AccessLogDBManager(connection));
            tables.add(new DevicesDBManager(connection));
            tables.add(new OrderDBManager(connection));
            tables.add(new OrderItemDBManager(connection));
            tables.add(new PaymentDBManager(connection));
            tables.add(new StaffDetail(connection));
            tables.add(new SupplierDBManager(connection));
            tables.add(new UserDBManager(connection));
        }
        catch (SQLException ex)
        {
=======
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
>>>>>>> a21e133 (Initial commit from IntelliJ)
            System.out.println("Error initializing DBManagers");
        }
    }

<<<<<<< HEAD
    public AccessLogDBManager AccessLog()
    {
        return (AccessLogDBManager) tables.get(0);
    }
}
=======
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

>>>>>>> a21e133 (Initial commit from IntelliJ)
