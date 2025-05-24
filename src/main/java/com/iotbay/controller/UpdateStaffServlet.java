import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateStaffServlet")
public class UpdateStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("user_id");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String isActive = request.getParameter("is_active");

        try { //
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

            PreparedStatement updateUser = conn.prepareStatement(
                    "UPDATE Users SET email = ?, phone = ?, is_active = ? WHERE user_id = ?"
            );
            updateUser.setString(1, email);
            updateUser.setString(2, phone);
            updateUser.setString(3, isActive);
            updateUser.setString(4, userId);
            updateUser.executeUpdate();

            PreparedStatement updateDetails = conn.prepareStatement(
                    "INSERT OR REPLACE INTO StaffDetails (staff_id, address) VALUES (?, ?)"
            );
            updateDetails.setString(1, userId);
            updateDetails.setString(2, address);
            updateDetails.executeUpdate();

            conn.close();
            response.sendRedirect("SystemAdmin.jsp?type=staff");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
