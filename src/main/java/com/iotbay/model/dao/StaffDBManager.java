package com.iotbay.model.dao;

import com.iotbay.model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDBManager {
    private Connection conn;

    public StaffDBManager(Connection conn) {
        this.conn = conn;
        createTable();
    }

    private void createTable() {


        String dropSql = "DROP TABLE IF EXISTS staff";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(dropSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String createSql = "CREATE TABLE IF NOT EXISTS staff (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "email TEXT NOT NULL UNIQUE," +
            "position TEXT NOT NULL," +
            "address TEXT NOT NULL," +
            "status TEXT NOT NULL DEFAULT 'ACTIVE'" +
            ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createSql);
            
            // Check if table is empty and insert sample data if needed
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM staff");
            if (rs.next() && rs.getInt(1) == 0) {
                insertSampleData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertSampleData() {
        String sql = "INSERT INTO staff (name, email, position, address, status) VALUES " +
            "('John Smith', 'john.smith@iotbay.com', 'Manager', '123 George Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Sarah Johnson', 'sarah.j@iotbay.com', 'Salesperson', '45 Pitt Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Michael Brown', 'michael.b@iotbay.com', 'IT Support', '78 Market Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Emma Wilson', 'emma.w@iotbay.com', 'Salesperson', '90 Castlereagh Street, Sydney NSW 2000', 'ACTIVE')," +
            "('David Lee', 'david.l@iotbay.com', 'Manager', '34 Macquarie Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Lisa Chen', 'lisa.c@iotbay.com', 'IT Support', '56 York Street, Sydney NSW 2000', 'ACTIVE')," +
            "('James Taylor', 'james.t@iotbay.com', 'Salesperson', '67 Clarence Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Sophie Anderson', 'sophie.a@iotbay.com', 'Manager', '89 Kent Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Robert White', 'robert.w@iotbay.com', 'IT Support', '12 Sussex Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Olivia Martin', 'olivia.m@iotbay.com', 'Salesperson', '23 Bridge Street, Sydney NSW 2000', 'ACTIVE')," +
            "('William Thompson', 'william.t@iotbay.com', 'Manager', '45 Martin Place, Sydney NSW 2000', 'ACTIVE')," +
            "('Isabella Garcia', 'isabella.g@iotbay.com', 'IT Support', '67 Elizabeth Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Daniel Martinez', 'daniel.m@iotbay.com', 'Salesperson', '89 Park Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Charlotte Robinson', 'charlotte.r@iotbay.com', 'Manager', '12 King Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Joseph Clark', 'joseph.c@iotbay.com', 'IT Support', '34 Hunter Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Amelia Rodriguez', 'amelia.r@iotbay.com', 'Salesperson', '56 Phillip Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Benjamin Lewis', 'benjamin.l@iotbay.com', 'Manager', '78 Bligh Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Mia Walker', 'mia.w@iotbay.com', 'IT Support', '90 Margaret Street, Sydney NSW 2000', 'ACTIVE')," +
            "('Lucas Hall', 'lucas.h@iotbay.com', 'Salesperson', '23 Macquarie Place, Sydney NSW 2000', 'ACTIVE')," +
            "('Harper Young', 'harper.y@iotbay.com', 'Manager', '45 Bridge Street, Sydney NSW 2000', 'ACTIVE')";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getString("address"),
                    rs.getString("status")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public List<Staff> searchStaff(String name, String position) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM staff WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        if (position != null && !position.trim().isEmpty()) {
            sql.append(" AND position LIKE ?");
            params.add("%" + position + "%");
        }
        
        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getString("address"),
                    rs.getString("status")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }

    public Staff getStaffById(int id) throws SQLException {
        String sql = "SELECT * FROM staff WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Staff(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("position"),
                    rs.getString("address"),
                    rs.getString("status")
                );
            }
        }
        return null;
    }

    public void addStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (name, email, position, address, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPosition());
            stmt.setString(4, staff.getAddress());
            stmt.setString(5, staff.getStatus());
            stmt.executeUpdate();
        }
    }

    public void updateStaff(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET name=?, email=?, position=?, address=?, status=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPosition());
            stmt.setString(4, staff.getAddress());
            stmt.setString(5, staff.getStatus());
            stmt.setInt(6, staff.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteStaff(int id) throws SQLException {
        String sql = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void toggleStatus(int id) throws SQLException {
        String sql = "UPDATE staff SET status = CASE WHEN status = 'ACTIVE' THEN 'INACTIVE' ELSE 'ACTIVE' END WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
} 