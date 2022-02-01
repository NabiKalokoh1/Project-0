package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://23.236.57.74:5432/bank";
        String username = "project";//System.getenv("DB_USER");
        String password = "nabiatu1";//System.getenv("DB_PASS");
        return DriverManager.getConnection(url, username, password);
    }
}
