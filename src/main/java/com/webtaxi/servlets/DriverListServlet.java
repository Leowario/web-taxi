package com.webtaxi.servlets;

import com.google.gson.Gson;
import com.webtaxi.model.Driver;
import com.webtaxi.sql.SQLDriverFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.PriorityQueue;

import static com.webtaxi.servlets.ResponseContentTypeSetter.setJsonType;

@WebServlet(
        name = "DriverList",
        urlPatterns = "/driverList"
)
public class DriverListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taxiClass = req.getParameter("taxiClass").toLowerCase();
        Optional<PriorityQueue<Driver>> optional = SQLDriverFacade.selectAllAvailableDriversByCarClass(taxiClass);
        if (optional.isPresent()) {
            PriorityQueue<Driver> drivers = optional.get();
            String json = new Gson().toJson(drivers);
            setJsonType(resp);
            resp.getWriter().write(json);
        } else {
            throw new ServletException();
        }
    }
}
