package com.iotbay.model.dao;

import com.iotbay.model.AccessLog;

import java.sql.Connection;
import java.sql.SQLException;

public class AccessLogDBManager extends DBManager<AccessLog>
{
    public AccessLogDBManager(Connection connection) throws SQLException
    {
        super(connection);
    }
}
