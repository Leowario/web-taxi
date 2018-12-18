package com.webtaxi.servlets;

import com.webtaxi.users.Customer;

import javax.servlet.http.Cookie;
import java.text.ParseException;
import java.util.Optional;

import static com.webtaxi.sql.SQLCustomerCommandExecutor.selectCustomerByLoginAndPassword;

public class CustomerParser {

    private CustomerParser() {

    }

    public static Optional<Customer> getCustomer(Cookie[] cookies) {
        String login = "";
        String password = "";
        for (Cookie cookie : cookies) {
            String key = cookie.getName();
            if ("login".equals(key)) {
                login = cookie.getValue();
            }
            if ("password".equals(key)) {
                password = cookie.getValue();
            }
        }
        return selectCustomerByLoginAndPassword(login, password);
    }

}
