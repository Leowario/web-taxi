package com.webtaxi.servlets;

import com.google.gson.Gson;
import com.webtaxi.sql.SQLRouteHistoryFacade;
import com.webtaxi.model.RouteHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setJsonType;

@WebServlet(
        name = "RouteHistory",
        urlPatterns = "/routeHistory"
)
public class RouteHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int custId = Integer.parseInt(req.getParameter("custId"));
        Optional<List<RouteHistory>> optional = SQLRouteHistoryFacade.selectAllRouteHistoryOfCustomer(custId);
        if (optional.isPresent()) {
            List<RouteHistory> routeHistoryList = optional.get();
            String json = new Gson().toJson(routeHistoryList);
            setJsonType(resp);
            resp.getWriter().write(json);
        }
    }
}
