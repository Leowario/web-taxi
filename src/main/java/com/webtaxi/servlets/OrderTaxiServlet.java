package com.webtaxi.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;

@WebServlet(
        name = "OrderTaxi",
        urlPatterns = "/orderTaxi"
)
public class OrderTaxiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setTextType(resp);
        resp.getWriter().write("Success");
    }
}
