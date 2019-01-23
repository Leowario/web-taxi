package com.webtaxi.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vitalii Usatyi
 */
public class SQLConnectionFactory {
    private static final String DB_URL = "jdbc:mysql://Asus_home:3306/web_taxi?useSSL=false&serverTimezone=UTC";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
