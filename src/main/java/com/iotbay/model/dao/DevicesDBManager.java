package com.iotbay.model.dao;

import com.iotbay.model.Devices;
<<<<<<< HEAD

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
=======
import com.iotbay.model.User;

import java.sql.*;
import java.util.LinkedList;
>>>>>>> a21e133 (Initial commit from IntelliJ)

public class DevicesDBManager extends DBManager<Devices>
{
    public DevicesDBManager(Connection connection) throws SQLException
    {
        super(connection);
    }

    public Devices add(Devices device) throws SQLException
    {
<<<<<<< HEAD
        PreparedStatement ps = connection.prepareStatement("");
    }
=======
        PreparedStatement ps = connection.prepareStatement("INSERT INTO IoTDevices (device_name, device_type, unit_price, quantity, created_by) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, device.getDeviceName());
        ps.setString(2, device.getDeviceType());
        ps.setDouble(3, device.getDevicePrice());
        ps.setInt(4, device.getDeviceQuantity());
        if (device.getCreatedByUserId() != null) {
            ps.setInt(5, device.getCreatedByUserId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }
        ps.executeUpdate();

        ps = connection.prepareStatement("SELECT MAX(device_id) FROM IoTDevices");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int deviceId = rs.getInt(1);
        device.setDeviceId(deviceId);
        return device;
    }

    public Devices get(Devices device) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM IoTDevices WHERE device_id = ?");
        ps.setInt(1, device.getDeviceId());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Devices(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getInt(5), rs.getInt(6));

    }

    public void update(Devices oldDevices, Devices newDevices) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("UPDATE IoTDevices SET device_name = ?, device_type = ?, unit_price = ?, quantity = ?, created_by = ? WHERE device_id = ?");
        ps.setString(1, newDevices.getDeviceName());
        ps.setString(2, newDevices.getDeviceType());
        ps.setDouble(3, newDevices.getDevicePrice());
        ps.setInt(4, newDevices.getDeviceQuantity());
        if (newDevices.getCreatedByUserId() != null) {
            ps.setInt(5, newDevices.getCreatedByUserId());
        } else {
            ps.setNull(5, java.sql.Types.INTEGER);
        }

        ps.setInt(6, oldDevices.getDeviceId());
        ps.executeUpdate();
    }

    public void delete(Devices device) throws SQLException
    {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM IoTDevices WHERE device_id = ?");
        ps.setInt(1, device.getDeviceId());
        ps.executeUpdate();
    }

    public LinkedList<Devices> allDevices() throws SQLException
    {
        LinkedList<Devices> devices = new LinkedList<>();
        String sql = "SELECT * FROM IoTDevices";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Devices device = new Devices(rs.getInt("device_id"), rs.getString("device_name"), rs.getString("device_type"), rs.getDouble("unit_price"), rs.getInt("quantity"), rs.getInt("created_by"));
            devices.add(device);
        }
        return devices;
    }

>>>>>>> a21e133 (Initial commit from IntelliJ)
}
