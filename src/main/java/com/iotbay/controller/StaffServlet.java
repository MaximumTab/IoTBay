package com.iotbay.controller;

import com.iotbay.model.Staff;
import com.iotbay.model.dao.DBConnector;
import com.iotbay.model.dao.StaffDBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private StaffDBManager staffDB;

    @Override
    public void init() throws ServletException {
        try {
            DBConnector dbConnector = new DBConnector();
            staffDB = new StaffDBManager(dbConnector.getConnection());
        } catch (Exception e) {
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "list":
                    listStaff(request, response);
                    break;
                case "addForm":
                    showAddForm(request, response);
                    break;
                case "editForm":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteStaff(request, response);
                    break;
                case "toggle":
                    toggleStatus(request, response);
                    break;
                default:
                    listStaff(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action) {
                case "add":
                    addStaff(request, response);
                    break;
                case "edit":
                    updateStaff(request, response);
                    break;
                default:
                    listStaff(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        
        List<Staff> staffList;
        if (name != null || position != null) {
            staffList = staffDB.searchStaff(name, position);
        } else {
            staffList = staffDB.getAllStaff();
        }
        
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("/staff/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/staff/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffDB.getStaffById(id);
        if (staff != null) {
            request.setAttribute("staff", staff);
        }
        request.getRequestDispatcher("/staff/form.jsp").forward(request, response);
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        Staff staff = new Staff();
        staff.setName(request.getParameter("name"));
        staff.setEmail(request.getParameter("email"));
        staff.setPosition(request.getParameter("position"));
        staff.setAddress(request.getParameter("address"));
        staff.setStatus(request.getParameter("status"));

        staffDB.addStaff(staff);
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(request.getParameter("id")));
        staff.setName(request.getParameter("name"));
        staff.setEmail(request.getParameter("email"));
        staff.setPosition(request.getParameter("position"));
        staff.setAddress(request.getParameter("address"));
        staff.setStatus(request.getParameter("status"));

        staffDB.updateStaff(staff);
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffDB.deleteStaff(id);
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void toggleStatus(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffDB.toggleStatus(id);
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }
} 