package com.webtaxi.servlets;

import com.webtaxi.users.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setContentType;
import static com.webtaxi.sql.SQLCustomerCommandExecutor.selectCustomerByLoginAndPassword;

/**
 * @author Vitalii Usatyi
 */
@WebServlet(
        name = "SignIn",
        urlPatterns = "/signIn"
)
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Customer> optional = selectCustomerByLoginAndPassword(login, password);
        if (optional.isPresent()) {
            setContentType(resp);
            resp.getWriter().write("Success");
        } else {
            setContentType(resp);
            resp.getWriter().write("Incorrect login or password");
        }
    }
}
