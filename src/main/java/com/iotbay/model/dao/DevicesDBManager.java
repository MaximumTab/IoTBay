package com.iotbay.model.dao;

import com.iotbay.model.Devices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevicesDBManager extends DBManager<Devices>
{
    public DevicesDBManager(Connection connection) throws SQLException
    {
        super(connection);
    }

    public Devices add(Devices device) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("");
    }
}
