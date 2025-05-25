package com.iotbay.controller;

import com.iotbay.model.Staff;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private Connection conn;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS staff");
                
                stmt.execute("CREATE TABLE staff (" +
                        "staff_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "email TEXT NOT NULL UNIQUE," +
                        "position TEXT NOT NULL," +
                        "address TEXT NOT NULL," +
                        "status TEXT NOT NULL DEFAULT 'ACTIVE')");
                
                stmt.execute("INSERT INTO staff (name, email, position, address, status) VALUES " +
                        "('John Smith', 'john.smith@iotbay.com', 'Manager', '123 George Street, Sydney NSW 2000', 'ACTIVE')," +
                        "('Sarah Johnson', 'sarah.j@iotbay.com', 'Salesperson', '45 Queen Street, Brisbane QLD 4000', 'ACTIVE')," +
                        "('Michael Brown', 'michael.b@iotbay.com', 'IT Support', '78 Swanston Street, Melbourne VIC 3000', 'ACTIVE')," +
                        "('Emma Wilson', 'emma.w@iotbay.com', 'Salesperson', '12 Murray Street, Perth WA 6000', 'ACTIVE')," +
                        "('David Lee', 'david.l@iotbay.com', 'Manager', '34 Hindley Street, Adelaide SA 5000', 'ACTIVE')," +
                        "('Lisa Chen', 'lisa.c@iotbay.com', 'IT Support', '56 Elizabeth Street, Hobart TAS 7000', 'ACTIVE')," +
                        "('James Taylor', 'james.t@iotbay.com', 'Salesperson', '89 Hay Street, Sydney NSW 2000', 'ACTIVE')," +
                        "('Sophie Martin', 'sophie.m@iotbay.com', 'Manager', '23 Collins Street, Melbourne VIC 3000', 'ACTIVE')," +
                        "('Robert White', 'robert.w@iotbay.com', 'IT Support', '67 Rundle Street, Adelaide SA 5000', 'ACTIVE')," +
                        "('Olivia Davis', 'olivia.d@iotbay.com', 'Salesperson', '45 Murray Street, Perth WA 6000', 'ACTIVE')," +
                        "('William Thompson', 'william.t@iotbay.com', 'Manager', '78 George Street, Sydney NSW 2000', 'ACTIVE')," +
                        "('Charlotte Anderson', 'charlotte.a@iotbay.com', 'IT Support', '12 Queen Street, Brisbane QLD 4000', 'ACTIVE')," +
                        "('Daniel Harris', 'daniel.h@iotbay.com', 'Salesperson', '34 Swanston Street, Melbourne VIC 3000', 'ACTIVE')," +
                        "('Amelia Clark', 'amelia.c@iotbay.com', 'Manager', '56 Hindley Street, Adelaide SA 5000', 'ACTIVE')," +
                        "('Joseph Lewis', 'joseph.l@iotbay.com', 'IT Support', '89 Elizabeth Street, Hobart TAS 7000', 'ACTIVE')," +
                        "('Mia Walker', 'mia.w@iotbay.com', 'Salesperson', '23 Hay Street, Sydney NSW 2000', 'ACTIVE')," +
                        "('Henry Hall', 'henry.h@iotbay.com', 'Manager', '67 Collins Street, Melbourne VIC 3000', 'ACTIVE')," +
                        "('Ella Young', 'ella.y@iotbay.com', 'IT Support', '45 Rundle Street, Adelaide SA 5000', 'ACTIVE')," +
                        "('Alexander King', 'alexander.k@iotbay.com', 'Salesperson', '78 Murray Street, Perth WA 6000', 'ACTIVE')," +
                        "('Grace Wright', 'grace.w@iotbay.com', 'Manager', '12 George Street, Sydney NSW 2000', 'ACTIVE')");
            }
        } catch (Exception e) {
            throw new ServletException("Failed to initialize database connection", e);
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
                    response.sendRedirect(request.getContextPath() + "/staff?action=list");
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void listStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        
        String sql = "SELECT * FROM staff WHERE 1=1";
        List<String> params = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            sql += " AND name LIKE ?";
            params.add("%" + name + "%");
        }
        if (position != null && !position.trim().isEmpty()) {
            sql += " AND position LIKE ?";
            params.add("%" + position + "%");
        }
        
        List<Staff> staffList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("staff_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getString("address"),
                    rs.getString("status")
                );
                staffList.add(staff);
            }
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
        String sql = "SELECT * FROM staff WHERE staff_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("staff_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getString("address"),
                    rs.getString("status")
                );
                request.setAttribute("staff", staff);
            }
        }
        
        request.getRequestDispatcher("/staff/form.jsp").forward(request, response);
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String sql = "INSERT INTO staff (name, email, position, address, status) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, request.getParameter("name"));
            stmt.setString(2, request.getParameter("email"));
            stmt.setString(3, request.getParameter("position"));
            stmt.setString(4, request.getParameter("address"));
            stmt.setString(5, request.getParameter("status"));
            stmt.executeUpdate();
        }
        
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        String sql = "UPDATE staff SET name=?, email=?, position=?, address=?, status=? WHERE staff_id=?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, request.getParameter("name"));
            stmt.setString(2, request.getParameter("email"));
            stmt.setString(3, request.getParameter("position"));
            stmt.setString(4, request.getParameter("address"));
            stmt.setString(5, request.getParameter("status"));
            stmt.setInt(6, Integer.parseInt(request.getParameter("staffId")));
            stmt.executeUpdate();
        }
        
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "DELETE FROM staff WHERE staff_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    private void toggleStatus(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String sql = "UPDATE staff SET status = CASE WHEN status = 'ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END WHERE staff_id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        
        response.sendRedirect(request.getContextPath() + "/staff?action=list");
    }

    @Override
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 