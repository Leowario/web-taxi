package com.webtaxi.model;

/**
 * @author Vitalii Usatyi
 */
public class Car {
    private String carModel;
    private String carClass;

    public Car(String carModel, String carClass) {
        this.carModel = carModel;
        this.carClass = carClass;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarClass() {
        return carClass;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carModel='" + carModel + '\'' +
                ", carClass='" + carClass + '\'' +
                '}';
    }
}
