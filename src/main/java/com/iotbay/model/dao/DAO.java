package com.iotbay.model.dao;

import com.iotbay.model.AccessLog;
import com.iotbay.model.OrderItem;
import com.iotbay.model.StaffDetail;
import com.iotbay.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    ArrayList<DBManager<?>> tables;

    public DAO() throws SQLException {
        tables = new ArrayList<>();
        Connection connection = new DBConnector().getConnection();
        try {
            //tables.add(new AccessLogDBManager(connection));
            tables.add(new DevicesDBManager(connection));
            //tables.add(new OrderDBManager(connection));
            //tables.add(new OrderItemDBManager(connection));
            //tables.add(new PaymentDBManager(connection));
            //tables.add(new StaffDetail(connection));
            //tables.add(new SupplierDBManager(connection));
            //tables.add(new UserDBManager(connection));
        } catch (SQLException ex) {
            System.out.println("Error initializing DBManagers");
        }
    }

    public DevicesDBManager Devices() {
        return (DevicesDBManager) tables.get(0);
    }

}


