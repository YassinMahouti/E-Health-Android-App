package com.example.iliasspush.database.dao;

import androidx.room.Database;

import com.example.iliasspush.User;

import java.sql.SQLException;
import java.sql.Statement;

public class User2DAO extends BaseDAO {
    public void saveUser(User user)
    {

        try {
            Statement s = getConn().createStatement();
            s.executeUpdate("insert into User (id, name) values ("+user.getId()+ "," +user.getName()+")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
