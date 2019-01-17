package com.webtaxi.users;

public class Order {
    private String startPoint;
    private String endPoint;
    private TaxiClass taxiclass;

    public Order(String startPoint, String endPoint, TaxiClass taxiclass) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.taxiclass = taxiclass;
    }


}
