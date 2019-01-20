package com.webtaxi.servlets;

import com.google.gson.Gson;
import com.webtaxi.sql.SQLCustomerFacade;
import com.webtaxi.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setJsonType;
import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;

@WebServlet(
        name = "CustomerProfile",
        urlPatterns = "/customerProfile"
)
public class CustomerProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Customer> optional = SQLCustomerFacade.selectCustomerByLoginAndPassword(login, password);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            String json = new Gson().toJson(customer);
            setJsonType(resp);
            resp.getWriter().write(json);
        } else {
            setTextType(resp);
            resp.getWriter().write("Internal Error");
        }
    }
}
