package com.webtaxi.sql;

import com.webtaxi.model.Route;
import com.webtaxi.model.RouteHistory;
import com.webtaxi.model.RouteHistoryFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.webtaxi.sql.SQLRouteHistoryCommand.*;
import static com.webtaxi.sql.SQLStatementFactory.getPreparedStatement;
import static com.webtaxi.sql.SQLStatementFactory.getStatement;

/**
 * @author Vitalii Usatyi
 */
public class SQLRouteHistoryFacade {

    private SQLRouteHistoryFacade() {

    }

    public static void createTableRouteHistory() {
        try (Statement statement = getStatement()) {
            statement.execute(CREATE_TABLE_ROUTE_HISTORY.sql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addRouteToRouteHistory(Route route) {
        try (PreparedStatement preparedStatement = getPreparedStatement(ADD_ROUTE_TO_ROUTE_HISTORY.sql())) {
            int index = 1;
            preparedStatement.setInt(index, route.getCustomerId());
            preparedStatement.setInt(++index, route.getDriverId());
            preparedStatement.setString(++index, route.getStartPoint());
            preparedStatement.setString(++index, route.getEndPoint());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
