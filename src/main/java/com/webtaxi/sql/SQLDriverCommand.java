package com.webtaxi.sql;

public enum SQLDriverCommand {
    ADD_DRIVER("INSERT INTO web_taxi.drivers(" +
            "first_name,last_name, car_model,car_class, rating, is_free)" +
            "VALUES (?,?,?,?,?,?) "),

    CREATE_TABLE_DRIVERS("CREATE TABLE IF NOT EXISTS drivers(" +
            "driv_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "first_name VARCHAR(255) NOT NULL UNIQUE," +
            "last_name VARCHAR(255) NOT NULL UNIQUE," +
            "car_model VARCHAR(255) NOT NULL," +
            "car_class VARCHAR(255) NOT NULL," +
            "rating INT(10) NOT NULL," +
            "is_free BOOLEAN)"),
    SELECT_ALL_FROM_DRIVERS("SELECT * FROM drivers"),
    SELECT_ALL_AVAILABLE_DRIVERS_BY_CAR_CLASS("SELECT driv_id, first_name,last_name,car_class,car_model,rating " +
            "FROM web_taxi.drivers " +
            "where " +
            "is_free = true AND car_class = ?");
    private final String sql;

    public String sql() {
        return this.sql;
    }

    SQLDriverCommand(String sql) {
        this.sql = sql;
    }


}
