package com.webtaxi.sql;

/**
 * @author Vitalii Usatiy
 */
public enum SQLCommand {
    ADD_CUSTOMER("INSERT INTO web_taxi.customers(" +
            "login,password,first_name,last_name, rating)" +
            "VALUES (?,?,?,?,?) "),
    ADD_DRIVER("INSERT INTO web_taxi.drivers(" +
            "first_name,last_name, car_model,car_class, rating, is_free)" +
            "VALUES (?,?,?,?,?,?) "),
    CREATE_TABLE_CUSTOMERS("CREATE TABLE IF NOT EXISTS customers(" +
            "cust_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "login VARCHAR(255) NOT NULL UNIQUE," +
            "password VARCHAR(255) NOT NULL," +
            "first_name VARCHAR(255) NOT NULL," +
            "last_name VARCHAR(255) NOT NULL," +
            "rating INT(10))"),
    CREATE_TABLE_DRIVERS("CREATE TABLE IF NOT EXISTS drivers(" +
            "driv_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "first_name VARCHAR(255) NOT NULL UNIQUE," +
            "last_name VARCHAR(255) NOT NULL UNIQUE," +
            "car_model VARCHAR(255) NOT NULL," +
            "car_class VARCHAR(255) NOT NULL," +
            "rating INT(10) NOT NULL," +
            "is_free BOOLEAN)"),
    SELECT_ALL_FROM_CUSTOMERS("SELECT * FROM customers"),
    SELECT_CUSTOMER_BY_LOGIN_PASSWORD("SELECT login, first_name,last_name,rating FROM web_taxi.customers " +
            "WHERE login = ? and password = ?"),
DELETE_USER_BY_LOGIN("DELETE FROM  web_taxi.customers WHERE login=?"),
    SELECT_ALL_FROM_DRIVERS("SELECT * FROM drivers"),
    SELECT_ALL_AVAILABLE_DRIVERS("SELECT first_name,last_name,car_class,car_model,rating" +
            "FROM drivers" +
            "where is_free = true");

    private final String sql;

    public String sql() {
        return this.sql;
    }

    SQLCommand(String sql) {
        this.sql = sql;
    }
}
