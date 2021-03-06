package com.webtaxi.servlets;

import com.webtaxi.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;
import static com.webtaxi.sql.SQLCustomerFacade.selectCustomerByLoginAndPassword;

/**
 * @author Vitalii Usatyi
 */
@WebServlet(
        name = "SignIn",
        urlPatterns = "/signIn"
)
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Customer> optional = selectCustomerByLoginAndPassword(login, password);
        if (optional.isPresent()) {
            req.setAttribute("customer", optional.get());
            setTextType(resp);
            resp.getWriter().write("Success");
        } else {
            setTextType(resp);
            resp.getWriter().write("Incorrect login or password");
        }
    }
}
