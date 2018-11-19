package com.webtaxi.users;

import com.google.common.base.Preconditions;

public class Customer {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int rating;

    public Customer(String login, String password, String firstName, String lastName, int rating) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRating() {
        return rating;
    }

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private int rating;

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Customer build() {
            Preconditions.checkNotNull(login);
            Preconditions.checkNotNull(password);
            Preconditions.checkNotNull(firstName);
            Preconditions.checkNotNull(lastName);
            return new Customer(login, password, firstName, lastName, rating);
        }

    }
}
