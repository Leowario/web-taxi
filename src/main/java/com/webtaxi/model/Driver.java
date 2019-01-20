package com.webtaxi.model;

import com.google.common.base.Preconditions;

/**
 * @author Vitalii Usatyi
 */
public class Driver implements Comparable<Driver> {
    private String firstName;
    private String lastName;
    private Car car;
    private int rating;
    private boolean isFree;

    private Driver(String firstName, String lastName, Car car, int rating, boolean isFree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.car = car;
        this.rating = rating;
        this.isFree = isFree;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Car getCar() {
        return car;
    }

    public int getRating() {
        return rating;
    }

    public boolean isFree() {
        return isFree;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int compareTo(Driver o) {
        return (Integer.compare(o.rating, rating));//highest to up
    }

    @Override
    public String toString() {
        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", car=" + car.toString() +
                ", rating=" + rating +
                ", isFree=" + isFree +
                '}';
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Car car;
        private int rating;
        private boolean isFree;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setFree(boolean free) {
            isFree = free;
            return this;
        }

        public Driver build() {
            Preconditions.checkNotNull(firstName);
            Preconditions.checkNotNull(lastName);
            Preconditions.checkNotNull(car);
            return new Driver(firstName, lastName, car, rating, isFree);
        }
    }
}
