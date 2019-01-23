package com.webtaxi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;

/**
 * This class was created for checking connection with internal logic.
 * If servlet did not get request, customer will not go to the next page and get a message with error.
 */
@WebServlet(
        name = "routeDetails",
        urlPatterns = "/routeDetails"
)
public class RouteDetailsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setTextType(resp);
        resp.getWriter().write("Success");
    }
}
