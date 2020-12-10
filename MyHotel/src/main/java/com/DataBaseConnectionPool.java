package com;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnectionPool {
    private static DataBaseConnectionPool instance = null;

    private DataBaseConnectionPool() {
    }

    public static DataBaseConnectionPool getInstance() {
        if (instance == null) {
            instance = new DataBaseConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Context initialContext;
        Connection connection = null;
        try {
            initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/MyHotelPool");
            connection = dataSource.getConnection();
        }
        catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeStatement(Statement statement, Logger logger) {
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                logger.error("There are problems with statement closing:" + e.getLocalizedMessage());
            }
        }
    }

}
