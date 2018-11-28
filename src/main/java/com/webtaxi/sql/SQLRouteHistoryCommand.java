package com.webtaxi.sql;

public enum SQLRouteHistoryCommand {
    CREATE_TABLE_ROUTE_HISTORY("CREATE TABLE IF NOT EXISTS route_history(" +
            "route_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT," +
            "customer_id int," +
            "FOREIGN KEY (customer_id) references customers(cust_id) on delete cascade," +
            "driver_id   int," +
            "FOREIGN KEY (driver_id) references drivers(driv_id) on delete cascade," +
            "start_point  VARCHAR(255) NOT NULL," +
            "end_point   VARCHAR(255) NOT NULL)"),
    SELECT_ROUTE_HISTORY_OF_CUSTOMER("SELECT start_point, end_point,drivers.first_name," +
            "drivers.last_name ,drivers.rating," +
            " drivers.car_model," +
            "drivers.car_class FROM route_history , drivers " +
            "WHERE route_history.customer_id = ? " +
            "AND route_history.driver_id = drivers.driv_id"),
    ADD_ROUTE_TO_ROUTE_HISTORY("INSERT INTO route_history(customer_id,driver_id,start_point,end_point) VALUES (?,?,?,?)"),
    DELETE_ROUTE_BY_ID("DELETE FROM route_history where start_point= ?");

    private final String sql;

    public String sql() {
        return this.sql;
    }

    SQLRouteHistoryCommand(String sql) {
        this.sql = sql;
    }

}
