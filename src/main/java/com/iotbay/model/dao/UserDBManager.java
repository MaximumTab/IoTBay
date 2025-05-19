package com.iotbay.model.dao;

import com.iotbay.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDBManager  extends DBManager<User>
{
    public UserDBManager(Connection connection) throws SQLException
    {
        super(connection);
    }


    protected User add(User object) throws SQLException {
        return null;
    }


    protected User get(User object) throws SQLException {
        return null;
    }


    protected void update(User oldObject, User newObject) throws SQLException {

    }


    protected void delete(User object) throws SQLException {

    }
}
