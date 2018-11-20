package com.webtaxi.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.webtaxi.sql.SQLConnectionFactory.getConnection;
import static com.webtaxi.sql.SQLDriver.addDriverSQLToClassPath;

class SQLStatementFactory {

    static Statement getStatement() throws SQLException {
        addDriverSQLToClassPath();
        Connection connection = getConnection();
        return connection.createStatement();
    }

    static PreparedStatement getPreparedStatement(String sqlCommand) throws SQLException {
        addDriverSQLToClassPath();
        Connection connection = getConnection();
        return connection.prepareStatement(sqlCommand);
    }
}

