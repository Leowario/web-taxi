package com.webtaxi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Vitalii Usatiy
 * <p>
 * Creates a Customer by resultSet of executed SQL query.
 */
public class CustomerFactory {
    private CustomerFactory() {

    }

    public static Customer createCustomer(ResultSet resultSet) throws SQLException {
        return Customer.builder()
                .setCustId(resultSet.getInt("cust_id"))
                .setLogin(resultSet.getString("login"))
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name"))
                .setRating(resultSet.getInt("rating"))
                .build();
    }
}
