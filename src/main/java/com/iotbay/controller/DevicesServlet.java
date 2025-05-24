package com.iotbay.controller;

import com.iotbay.model.Devices;
import com.iotbay.model.User;
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
import java.util.stream.Collectors;

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
            LinkedList<Devices> allDevices = dao.Devices().allDevices();

            LinkedList<String> deviceTypes = allDevices.stream()
                    .map(Devices::getDeviceType)
                    .distinct()
                    .collect(Collectors.toCollection(LinkedList::new));

            String searchName = req.getParameter("searchName");
            String[] selectedTypes = req.getParameterValues("type");

            LinkedList<Devices> filteredDevices = allDevices.stream()
                    .filter(device -> {
                        boolean nameMatches = (searchName == null || searchName.isEmpty())
                                || device.getDeviceName().toLowerCase().contains(searchName.toLowerCase());

                        boolean typeMatches = (selectedTypes == null || selectedTypes.length == 0)
                                || java.util.Arrays.asList(selectedTypes).contains(device.getDeviceType());

                        return nameMatches && typeMatches;
                    })
                    .collect(Collectors.toCollection(LinkedList::new));

            User currentUser = (User) session.getAttribute("user");
            boolean isStaff = currentUser != null && "staff".equalsIgnoreCase(currentUser.getUserType());
            req.setAttribute("isStaff", isStaff);

            req.setAttribute("deviceList", filteredDevices);
            req.setAttribute("deviceTypes", deviceTypes);
            req.setAttribute("searchName", searchName);
            req.setAttribute("selectedTypes", selectedTypes);

            req.getRequestDispatcher("DevicesListView.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving device list", e);
        }
    }
}

