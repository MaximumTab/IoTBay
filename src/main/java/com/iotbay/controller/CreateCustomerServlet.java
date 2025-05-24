import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/CreateCustomerServlet")
public class CreateCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password_hash");
        String phone = request.getParameter("phone");
        String userType = request.getParameter("user_type");
        String address = request.getParameter("address");

        boolean emailExists = false;
        boolean phoneExists = false;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

            // Check if email exists
            PreparedStatement emailCheck = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE email = ?");
            emailCheck.setString(1, email);
            ResultSet rsEmail = emailCheck.executeQuery();
            if (rsEmail.next() && rsEmail.getInt(1) > 0) {
                emailExists = true;
            }

            // Check if phone exists
            PreparedStatement phoneCheck = conn.prepareStatement("SELECT COUNT(*) FROM Users WHERE phone = ?");
            phoneCheck.setString(1, phone);
            ResultSet rsPhone = phoneCheck.executeQuery();
            if (rsPhone.next() && rsPhone.getInt(1) > 0) {
                phoneExists = true;
            }

            conn.close();

            // Redirect to error page if either exists
            if (emailExists || phoneExists) {
                response.sendRedirect("UserExists.jsp?emailExists=" + emailExists + "&phoneExists=" + phoneExists);
                return;
            }

            // If both are unique, insert the new user
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/kevin/.SmartTomcat/IoTBay/IOTBayWebsite/IotBay.db");

            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO Users (full_name, email, password_hash, phone, user_type, is_active) " +
                            "VALUES (?, ?, ?, ?, ?, 1)"
            );
            insert.setString(1, fullName);
            insert.setString(2, email);
            insert.setString(3, password);
            insert.setString(4, phone);
            insert.setString(5, userType);
            insert.executeUpdate();

            conn.close();
            response.sendRedirect("SystemAdmin.jsp?type=" + userType);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}