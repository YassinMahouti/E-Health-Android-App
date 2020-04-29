package com.example.iliasspush.database.dao;


import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDAO {

    private Connection conn;

    public Connection getConn() {
        try {
            if (conn == null || conn.isClosed())
                conn = DataBaseSingleton.DatabaseSingleton.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public BaseDAO() {

    }
}