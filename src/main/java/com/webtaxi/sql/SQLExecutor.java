package com.webtaxi.sql;

import com.webtaxi.users.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import static com.webtaxi.sql.SQLCommand.*;
import static com.webtaxi.sql.SQLStatementFactory.getPreparedStatement;
import static com.webtaxi.sql.SQLStatementFactory.getStatement;
import static com.webtaxi.users.CustomerFactory.createCustomer;
import static com.webtaxi.users.DriverQueueFactory.getDriversQueue;

/**
 * @author Vitalii Usatyi
 */
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
            Customer customer = createCustomer(resultSet);
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

    public static Optional<PriorityQueue<Driver>> selectAllAvailableDriversByCarClass(String carClass) {
        try (PreparedStatement preparedStatement = getPreparedStatement(SELECT_ALL_AVAILABLE_DRIVERS_BY_CAR_CLASS.sql())) {
            int index = 1;
            preparedStatement.setString(index, carClass);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.of(getDriversQueue(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void selectAllFromCustomers() {
        try (Statement statement = getStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CUSTOMERS.sql())) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableRouteHistory() {
        try (Statement statement = getStatement()) {
            statement.execute(CREATE_TABLE_ROUTE_HISTORY.sql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRouteToRouteHistory(Route route) {
        try (PreparedStatement preparedStatement = getPreparedStatement(ADD_ROUTE_TO_ROUTE_HISTORY.sql())) {
            int index = 1;
            preparedStatement.setInt(index, route.getCustomerId());
            preparedStatement.setInt(++index, route.getDriverId());
            preparedStatement.setString(++index, route.getStartPoint());
            preparedStatement.setString(++index, route.getEndPoint());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Optional<List<RouteHistory>> selectAllRouteHistoryOfCustomer(int customerId) {
        try (PreparedStatement preparedStatement = getPreparedStatement(SELECT_ROUTE_HISTORY_OF_CUSTOMER.sql())) {
            int index = 1;
            preparedStatement.setInt(index, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RouteHistory> routeList = RouteHistoryFactory.getRouteList(resultSet);
            return Optional.of(routeList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void deleteRouteByStartPoint(String startPoint) {
        try (PreparedStatement preparedStatement = getPreparedStatement(DELETE_ROUTE_BY_ID.sql())) {
            int index = 1;
            preparedStatement.setString(index, startPoint);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
