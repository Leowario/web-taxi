package com.webtaxi.sql;

import com.webtaxi.users.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.PriorityQueue;

import static com.webtaxi.sql.SQLDriverCommand.*;
import static com.webtaxi.sql.SQLStatementFactory.getPreparedStatement;
import static com.webtaxi.sql.SQLStatementFactory.getStatement;
import static com.webtaxi.users.DriverQueueFactory.getDriversQueue;

/**
 * @author Vitalii Usatyi
 */
public class SQLDriverFacade {

    private SQLDriverFacade() {

    }

    public static void createTableDrivers() {
        String sqlCommand = CREATE_TABLE_DRIVERS.sql();
        try (Statement statement = getStatement()) {
            statement.execute(sqlCommand);
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
}
