package com.webtaxi.sql;

public enum SQLCustomerCommand {
    ADD_CUSTOMER("INSERT INTO web_taxi.customers(" +
            "login,password,first_name,last_name, rating)" +
            "VALUES (?,?,?,?,?) "),
    CREATE_TABLE_CUSTOMERS("CREATE TABLE IF NOT EXISTS customers(" +
            "cust_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "login VARCHAR(255) NOT NULL UNIQUE," +
            "password VARCHAR(255) NOT NULL," +
            "first_name VARCHAR(255) NOT NULL," +
            "last_name VARCHAR(255) NOT NULL," +
            "rating INT(10))"),
    SELECT_ALL_FROM_CUSTOMERS("SELECT * FROM customers"),
    SELECT_CUSTOMER_BY_LOGIN_PASSWORD("SELECT cust_id, login, first_name,last_name,rating FROM web_taxi.customers " +
            "WHERE login = ? and password = ?"),
    DELETE_USER_BY_LOGIN("DELETE FROM  web_taxi.customers WHERE login=?");

    private final String sql;

    public String sql() {
        return this.sql;
    }

    SQLCustomerCommand(String sql) {
        this.sql = sql;
    }

}
