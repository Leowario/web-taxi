package com.webtaxi.users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.PriorityQueue;

/**
 * @author Vitalii Usatyi
 */
public class DriverQueueFactory {

    private DriverQueueFactory() {

    }

    /**
     * @return A PriorityQueue of drivers with highest rating first.
     * @throws SQLException
     */
    public static PriorityQueue<Driver> getDriversQueue(ResultSet resultSet) throws SQLException {
        PriorityQueue<Driver> driversQueue = new PriorityQueue<>();
        while (resultSet.next()) {
            Driver driver = DriverFactory.createDriver(resultSet);
            driversQueue.add(driver);
        }
        return driversQueue;
    }
}
