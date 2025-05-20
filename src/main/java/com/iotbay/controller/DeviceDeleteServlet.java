package com.iotbay.controller;

import com.iotbay.model.Devices;
import com.iotbay.model.dao.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeviceDeleteServlet")
public class DeviceDeleteServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            int deviceId = Integer.parseInt(req.getParameter("selectedDeviceId"));
            Devices device = new Devices(deviceId, null, null, 0.0, 0, null);
            dao.Devices().delete(device);

            resp.sendRedirect(req.getContextPath() + "/DevicesServlet");

        } catch (Exception e) {
            throw new ServletException("Failed to delete device", e);
        }
    }
}


