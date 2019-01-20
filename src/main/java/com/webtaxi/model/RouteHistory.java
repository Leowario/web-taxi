package com.webtaxi.model;

/**
 * @author Vitalii Usatyi
 * <p>
 * This is a form for showing route to customer
 */
public class RouteHistory {
    private String startPoint;
    private String endPoint;
    private Driver driver;


    RouteHistory(String startPoint, String endPoint, Driver driver) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.driver = driver;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Driver getDriver() {
        return driver;
    }
}
