package com.webtaxi.servlets;

import com.webtaxi.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;
import static com.webtaxi.sql.SQLCustomerFacade.addCustomer;

/**
 * @author Vitalii Usatyi
 */
@WebServlet(
        name = "SingUp",
        urlPatterns = "/singUp"
)
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Customer customer = Customer.builder()
                .setLogin(login)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRating(3)//default
                .build();
        boolean result = addCustomer(customer);
        if (result) {
            setTextType(resp);
            resp.getWriter().write("Registration complete");
        } else {
            setTextType(resp);
            resp.getWriter().write("Login is already exists");
        }
    }
}
