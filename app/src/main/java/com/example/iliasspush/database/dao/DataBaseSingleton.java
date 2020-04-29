package com.example.iliasspush.database.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSingleton {

    public static class DatabaseSingleton {

        private Connection con;
        private static DatabaseSingleton instance;

        private DatabaseSingleton() {

        }


        public static DatabaseSingleton getInstance() {
            if (instance == null) {
                instance = new DatabaseSingleton();
            }
            return instance;
        }

        public Connection getConnection() {
            try {
                if (con == null || con.isClosed()) {
                    Connection con = DriverManager.getConnection("C:\\Users\\bilia\\OneDrive\\Documenten");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return con;
        }
    }
}
