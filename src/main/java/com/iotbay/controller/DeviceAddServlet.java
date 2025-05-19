package com.iotbay.controller;

import com.iotbay.model.Devices;
import com.iotbay.model.dao.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeviceAddServlet")
public class DeviceAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO dao = (DAO) session.getAttribute("db");

        if (dao == null) {
            try {
                dao = new DAO();
                session.setAttribute("db", dao);
            } catch (SQLException e) {
                throw new ServletException("DB init failed", e);
            }
        }

        String name = req.getParameter("deviceName");
        String type = req.getParameter("deviceType");
        double price = Double.parseDouble(req.getParameter("devicePrice"));
        int quantity = Integer.parseInt(req.getParameter("deviceQuantity"));

        try {
            Devices newDevice = new Devices(0, name, type, price, quantity, null);
            dao.Devices().add(newDevice);

            // Redirect back to the list
            resp.sendRedirect(req.getContextPath() + "/DevicesServlet");
        } catch (SQLException e) {
            throw new ServletException("Failed to add device", e);
        }
    }
}
