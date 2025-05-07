package com.iotbay.controller;

import com.iotbay.model.Devices;
import com.iotbay.model.dao.DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet("/DevicesServlet")
public class DevicesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        DAO dao = (DAO) session.getAttribute("db");

        if (dao == null) {
            try {
                dao = new DAO();
                session.setAttribute("db", dao);
            } catch (SQLException e) {
                throw new ServletException("Could not connect to DB", e);
            }
        }

        try {
            LinkedList<Devices> deviceList = dao.Devices().allDevices();
            req.setAttribute("deviceList", deviceList);
            req.getRequestDispatcher("DevicesListView.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving device list", e);
        }
    }
}

