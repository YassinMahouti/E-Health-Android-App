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
            System.out.println("TEST");
            try {
                if (con == null || con.isClosed()) {
                   // Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://193.190.238.37:3306/1920PROGPROJ_GR2","1920PROGPROJ_GR2","STpY5H9");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } /*catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
            System.out.println("TEST2");
            return con;
        }
    }
}
