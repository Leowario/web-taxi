package com.webtaxi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vitalii Usatyi
 */
public class DriverFactory {
    private DriverFactory() {

    }

    public static Driver createDriver(ResultSet resultSet) throws SQLException {
        return Driver.builder()
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name"))
                .setCar(new Car(
                        resultSet.getString("car_model"),
                        resultSet.getString("car_class")
                ))
                .setRating(resultSet.getInt("rating"))
                .build();
    }
}
