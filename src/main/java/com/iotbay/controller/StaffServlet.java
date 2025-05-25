package com.iotbay.controller;

// import your model/dao
import com.iotbay.model.Staff;
import com.iotbay.model.dao.StaffDAO;
import com.iotbay.model.dao.StaffDBManager;

// for a Jakarta (Tomcat 10+) container:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private StaffDAO dao = new StaffDBManager();
    
    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("addForm".equals(action)) {
                req.getRequestDispatcher("/staff/form.jsp").forward(req, resp);
            }
            else if ("editForm".equals(action)) {
                int id = validateId(req.getParameter("id"));
                Staff s = dao.getStaffById(id);
                if (s == null) {
                    req.setAttribute("error", "Staff not found");
                    resp.sendRedirect("staff?action=list");
                    return;
                }
                req.setAttribute("staff", s);
                req.getRequestDispatcher("/staff/form.jsp").forward(req, resp);
            }
            else if ("delete".equals(action)) {
                int id = validateId(req.getParameter("id"));
                if (!dao.deleteStaff(id)) {
                    req.setAttribute("error", "Failed to delete staff");
                }
                resp.sendRedirect("staff?action=list");
            }
            else if ("toggle".equals(action)) {
                int id = validateId(req.getParameter("id"));
                Staff s = dao.getStaffById(id);
                if (s == null) {
                    req.setAttribute("error", "Staff not found");
                } else {
                    if ("ACTIVE".equals(s.getStatus())) {
                        if (!dao.deactivateStaff(id)) {
                            req.setAttribute("error", "Failed to deactivate staff");
                        }
                    } else {
                        if (!dao.activateStaff(id)) {
                            req.setAttribute("error", "Failed to activate staff");
                        }
                    }
                }
                resp.sendRedirect("staff?action=list");
            }
            else { // default list (and search)
                String name = req.getParameter("name");
                String pos = req.getParameter("position");
                name = (name==null) ? "%" : "%" + name + "%";
                pos = (pos==null) ? "%" : "%" + pos + "%";
                List<Staff> list = dao.searchStaff(name, pos);
                req.setAttribute("staffList", list);
                req.getRequestDispatcher("/staff/list.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            handleError(req, resp, "Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            handleError(req, resp, "Invalid ID format");
        } catch (Exception e) {
            handleError(req, resp, "An unexpected error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            // Validate inputs first
            List<String> errors = validateStaffInput(req);
            if (!errors.isEmpty()) {
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/staff/form.jsp").forward(req, resp);
                return;
            }
            
            if ("add".equals(action)) {
                Staff s = extractStaff(req);
                if (dao.addStaff(s) < 0) {
                    handleError(req, resp, "Failed to add staff");
                    return;
                }
            } else if ("edit".equals(action)) {
                Staff s = extractStaff(req);
                s.setStaffId(validateId(req.getParameter("staffId")));
                if (!dao.updateStaff(s)) {
                    handleError(req, resp, "Failed to update staff");
                    return;
                }
            }
            resp.sendRedirect("staff?action=list");
        } catch (SQLException e) {
            handleError(req, resp, "Database error: " + e.getMessage());
        } catch (Exception e) {
            handleError(req, resp, "An unexpected error occurred");
        }
    }

    private Staff extractStaff(HttpServletRequest req) {
        Staff s = new Staff();
        s.setName(req.getParameter("name").trim());
        s.setEmail(req.getParameter("email").trim());
        s.setPosition(req.getParameter("position").trim());
        s.setAddress(req.getParameter("address").trim());
        s.setStatus(req.getParameter("status"));
        return s;
    }
    
    private List<String> validateStaffInput(HttpServletRequest req) {
        List<String> errors = new ArrayList<>();
        
        // Name validation
        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name is required");
        } else if (name.length() > 100) {
            errors.add("Name must be less than 100 characters");
        }
        
        // Email validation
        String email = req.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            errors.add("Email is required");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors.add("Invalid email format");
        }
        
        // Position validation
        String position = req.getParameter("position");
        if (position == null || position.trim().isEmpty()) {
            errors.add("Position is required");
        }
        
        // Address validation
        String address = req.getParameter("address");
        if (address == null || address.trim().isEmpty()) {
            errors.add("Address is required");
        }
        
        // Status validation
        String status = req.getParameter("status");
        if (status == null || (!status.equals("ACTIVE") && !status.equals("INACTIVE"))) {
            errors.add("Invalid status value");
        }
        
        return errors;
    }
    
    private int validateId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            if (id <= 0) throw new NumberFormatException();
            return id;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid ID format");
        }
    }
    
    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message) 
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.getRequestDispatcher("/staff/form.jsp").forward(req, resp);
    }
}
