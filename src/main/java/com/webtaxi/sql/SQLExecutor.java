package com.webtaxi.sql;

import com.webtaxi.users.Customer;
import com.webtaxi.users.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.webtaxi.sql.SQLCommand.*;
import static com.webtaxi.sql.SQLStatementFactory.getPreparedStatement;
import static com.webtaxi.sql.SQLStatementFactory.getStatement;

public class SQLExecutor {

    public static void createTableCustomers() {
        String sqlCommand = CREATE_TABLE_CUSTOMERS.sql();
        try (Statement statement = getStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableDrivers() {
        String sqlCommand = CREATE_TABLE_DRIVERS.sql();
        try (Statement statement = getStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customer customer) {
        try (PreparedStatement preparedStatement = getPreparedStatement(ADD_CUSTOMER.sql())) {
            int index = 1;
            preparedStatement.setString(index, customer.getLogin());
            preparedStatement.setString(++index, customer.getPassword());
            preparedStatement.setString(++index, customer.getFirstName());
            preparedStatement.setString(++index, customer.getLastName());
            preparedStatement.setInt(++index, customer.getRating());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDriver(Driver driver) {
        try (PreparedStatement preparedStatement = getPreparedStatement(ADD_DRIVER.sql())) {
            int index = 1;
            preparedStatement.setString(index, driver.getFirstName());
            preparedStatement.setString(++index, driver.getLastName());
            preparedStatement.setString(++index, driver.getCar().getCarModel());
            preparedStatement.setString(++index, driver.getCar().getCarClass());
            preparedStatement.setInt(++index, driver.getRating());
            preparedStatement.setBoolean(++index, driver.isFree());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Optional<Customer> selectCustomerByLoginAndPassword(String login, String password) {
        try (PreparedStatement preparedStatement = getPreparedStatement(SELECT_CUSTOMER_BY_LOGIN_PASSWORD.sql())) {
            int index = 1;
            preparedStatement.setString(index, login);
            preparedStatement.setString(++index, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Customer customer = Customer.builder()
                    .setLogin(resultSet.getString("login"))
                    .setFirstName(resultSet.getString("first_name"))
                    .setLastName(resultSet.getString("last_name"))
                    .setRating(resultSet.getInt("rating"))
                    .build();
            return Optional.of(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void deleteCustomerByLogin(String login) {
        try (PreparedStatement preparedStatement = getPreparedStatement(DELETE_USER_BY_LOGIN.sql())) {
            int index = 1;
            preparedStatement.setString(index, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAllFromCustomers() {
        try (Statement statement = getStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CUSTOMERS.sql())) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printResultSet(ResultSet resultSet) {
        try {
            resultSet.next();
            System.out.print(resultSet.getString("first_name") + " | ");
            System.out.print(resultSet.getString("last_name") + " | ");
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//
//    public static void selectAllAvailableDriversForCustomers(){
//        try (Statement statement = getStatement();
//             ResultSet resultSet = statement.executeQuery(INNER_JOIN_CUSTOMERS_DRIVERS.sql())) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


}
