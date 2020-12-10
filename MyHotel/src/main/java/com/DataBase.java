package com;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    private static final String url = "jdbc:mysql://localhost:3306/MyHotel";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void close(Connection connect) {
        try {
            connect.close();
        }
        catch (Exception e) {
        }
    }
}
