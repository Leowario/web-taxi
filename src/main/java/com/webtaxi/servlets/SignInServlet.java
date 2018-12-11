package com.webtaxi.servlets;

import com.google.common.net.MediaType;
import com.webtaxi.users.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static com.webtaxi.sql.SQLCustomerCommandExecutor.selectCustomerByLoginAndPassword;

@WebServlet(
        name = "SignIn",
        urlPatterns = "/signIn"
)
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Customer> optional = selectCustomerByLoginAndPassword(login, password);
        if (optional.isPresent()) {
            req.setAttribute("optional", optional.get());
            RequestDispatcher view = req.getRequestDispatcher("customerProfile.jsp");
            view.forward(req, resp);
        } else {
            //TODO
        }
    }
}
