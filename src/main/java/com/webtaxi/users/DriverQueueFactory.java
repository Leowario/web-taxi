package com.webtaxi.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PriorityQueue;

public class DriverQueueFactory {
    private DriverQueueFactory() {

    }

    public static PriorityQueue<Driver> getDriversQueue(ResultSet resultSet) throws SQLException {
        PriorityQueue<Driver> driversQueue = new PriorityQueue<>();
        while (resultSet.next()) {
            Driver driver = Driver.builder()

                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setCar(new Car(
                            resultSet.getString("car_model"),
                            resultSet.getString("car_class")
                    ))
                    .setRating(resultSet.getInt("rating"))
                    .build();
            driversQueue.add(driver);
        }
        return driversQueue;
    }
}
