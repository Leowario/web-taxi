package com.webtaxi.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Usatyy
 */
public class RouteHistoryFactory {

    private RouteHistoryFactory() {

    }

    public static List<RouteHistory> getRouteList(ResultSet resultSet) throws SQLException {
        List<RouteHistory> routeHistoryList = new ArrayList<>();
        while (resultSet.next()) {
            String start_point = resultSet.getString("start_point");
            String end_point = resultSet.getString("end_point");
            Driver driver = DriverFactory.createDriver(resultSet);
            routeHistoryList.add(new RouteHistory(start_point, end_point, driver));
        }
        return routeHistoryList;
    }
}
