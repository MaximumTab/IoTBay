import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;

import com.iotbay.model.User;
import com.iotbay.model.dao.UserDBManager;


@WebServlet("/staffLogin")
public class StaffLoginServlet extends HttpServlet {

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));
        return hex.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int staffId = Integer.parseInt(request.getParameter("staffId")); // CHANGED
        String password = request.getParameter("password");

        try {
            String hashedPassword = hashPassword(password);
            HttpSession session = request.getSession();
            Connection conn = (Connection) session.getAttribute("conn");

            UserDBManager userManager = new UserDBManager(conn);
            User staffUser = userManager.findStaffByIdAndPassword(staffId, hashedPassword); // NEW METHOD

            if (staffUser != null && staffUser.getIsActive().equals("1")) {
                session.setAttribute("user", staffUser);
                userManager.logUserLogin(staffUser.getId());
                response.sendRedirect("StaffDashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid credentials or not a staff account.");
                request.getRequestDispatcher("StaffLoginPage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("StaffLoginPage.jsp").forward(request, response);
        }
    }
}
