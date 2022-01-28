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

        String url = "jdbc:postgresql://23.236.57.74:5432/project0";
        //comeback and set this up
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        return DriverManager.getConnection(url, username, password);
    }
}
