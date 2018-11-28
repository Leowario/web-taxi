package com.webtaxi.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vitalii Usatyi
 */
public class SQLConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://192.168.1.71:3306/web_taxi?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
