package com.webtaxi.servlets;

import com.webtaxi.model.Route;
import com.webtaxi.sql.SQLRouteHistoryFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setTextType;

@WebServlet(
        name = "orderTaxi",
        urlPatterns = "/orderTaxi"
)
public class OrderTaxiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        int driverId = Integer.parseInt(req.getParameter("driverId"));
        String startPoint = req.getParameter("startPoint");
        String endPoint = req.getParameter("endPoint");
        boolean result = SQLRouteHistoryFacade.addRouteToRouteHistory(
                Route.builder()
                        .setCustomerId(customerId)
                        .setDriverId(driverId)
                        .setStartPoint(startPoint)
                        .setEndPoint(endPoint)
                        .build());
        setTextType(resp);
        if (result) {
            resp.getWriter().write("Success");
        } else {
            resp.getWriter().write("Error");
        }
    }
}
