package com.webtaxi.users;

import com.google.common.base.Preconditions;

/**
 * @author Vitalii Usatyi
 */
public class Route {
    private int route_id;
    private int customerId;
    private int driverId;
    private String startPoint;
    private String endPoint;

    private Route(int route_id, int customerId, int driverId, String startPoint, String endPoint) {
        this.route_id = route_id;
        this.customerId = customerId;
        this.driverId = driverId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public int getRoute_id() {
        return route_id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + route_id +
                ", customerId=" + customerId +
                ", driverId=" + driverId +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int routeId;
        private int customerId;
        private int driverId;
        private String startPoint;
        private String endPoint;

        public Builder setRouteId(int routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setDriverId(int driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder setStartPoint(String startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        public Builder setEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        public Route build() {
            Preconditions.checkNotNull(startPoint);
            Preconditions.checkNotNull(endPoint);
            return new Route(routeId, customerId, driverId, startPoint, endPoint);
        }
    }
}
