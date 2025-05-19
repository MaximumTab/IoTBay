package com.iotbay.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBManager<T>
{
    protected final Connection connection;
    protected final Statement statement;

    public DBManager(Connection connection) throws SQLException
    {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    protected abstract T add (T object) throws SQLException;
    protected abstract T get (T object) throws SQLException;
    protected abstract void update (T oldObject, T newObject) throws SQLException;
    protected abstract void delete (T object) throws SQLException;
}
