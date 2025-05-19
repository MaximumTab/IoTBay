package com.iotbay.controller;

import com.iotbay.model.Devices;
import com.iotbay.model.dao.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeviceEditServlet")
public class DeviceEditServlet extends HttpServlet {
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

        try {
            int id = Integer.parseInt(req.getParameter("deviceId"));
            String name = req.getParameter("deviceName");
            String type = req.getParameter("deviceType");

            String priceStr = req.getParameter("devicePrice");
            String quantityStr = req.getParameter("deviceQuantity");

            if (priceStr == null || quantityStr == null || name == null || type == null) {
                throw new ServletException("Missing form data");
            }

            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            Devices oldDevice = dao.Devices().get(new Devices(id, null, null, 0.0, 0, null));
            Devices updatedDevice = new Devices(id, name, type, price, quantity, oldDevice.getCreatedByUserId());

            dao.Devices().update(oldDevice, updatedDevice);

            resp.sendRedirect(req.getContextPath() + "/DevicesServlet");

        } catch (Exception e) {
            throw new ServletException("Failed to update device", e);
        }
    }
}
