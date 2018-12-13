package com.webtaxi.sql;

import com.webtaxi.users.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.webtaxi.sql.SQLCustomerCommand.*;
import static com.webtaxi.sql.SQLStatementFactory.getPreparedStatement;
import static com.webtaxi.sql.SQLStatementFactory.getStatement;
import static com.webtaxi.users.CustomerFactory.createCustomer;

/**
 * @author Vitalii Usatyi
 */
public class SQLCustomerCommandExecutor {

    private SQLCustomerCommandExecutor() {

    }

    public static void createTableCustomers() {
        String sqlCommand = CREATE_TABLE_CUSTOMERS.sql();
        try (Statement statement = getStatement()) {
            statement.execute(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addCustomer(Customer customer) {
        try (PreparedStatement preparedStatement = getPreparedStatement(ADD_CUSTOMER.sql())) {
            int index = 1;
            preparedStatement.setString(index, customer.getLogin());
            preparedStatement.setString(++index, customer.getPassword());
            preparedStatement.setString(++index, customer.getFirstName());
            preparedStatement.setString(++index, customer.getLastName());
            preparedStatement.setInt(++index, customer.getRating());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public static void selectAllFromCustomers() {
        try (Statement statement = getStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_CUSTOMERS.sql())) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

