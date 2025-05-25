package com.iotbay.model.dao;

import com.iotbay.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StaffDBManager implements StaffDAO {

    // Use your existing DBConnector instance to get a JDBC Connection
    private Connection getConn() throws SQLException {
        return new DBConnector().getConnection();
    }

    @Override
    public int addStaff(Staff s) throws SQLException {
        String sql = "INSERT INTO Staff(name,email,position,address,status) VALUES(?,?,?,?,?)";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            p.setString(1, s.getName());
            p.setString(2, s.getEmail());
            p.setString(3, s.getPosition());
            p.setString(4, s.getAddress());
            p.setString(5, s.getStatus());
            p.executeUpdate();

            try (ResultSet rs = p.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : -1;
            }
        }
    }

    @Override
    public Staff getStaffById(int id) throws SQLException {
        String sql = "SELECT * FROM Staff WHERE staff_id=?";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, id);
            try (ResultSet r = p.executeQuery()) {
                return r.next() ? mapRow(r) : null;
            }
        }
    }

    @Override
    public List<Staff> getAllStaff() throws SQLException {
        String sql = "SELECT * FROM Staff";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql);
             ResultSet r = p.executeQuery()) {

            List<Staff> list = new ArrayList<>();
            while (r.next()) {
                list.add(mapRow(r));
            }
            return list;
        }
    }

    @Override
    public List<Staff> searchStaff(String namePattern, String positionPattern) throws SQLException {
        String sql = "SELECT * FROM Staff WHERE name LIKE ? AND position LIKE ?";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, namePattern);
            p.setString(2, positionPattern);
            try (ResultSet r = p.executeQuery()) {
                List<Staff> list = new ArrayList<>();
                while (r.next()) {
                    list.add(mapRow(r));
                }
                return list;
            }
        }
    }

    @Override
    public boolean updateStaff(Staff s) throws SQLException {
        String sql = "UPDATE Staff SET name=?,email=?,position=?,address=?,status=? WHERE staff_id=?";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, s.getName());
            p.setString(2, s.getEmail());
            p.setString(3, s.getPosition());
            p.setString(4, s.getAddress());
            p.setString(5, s.getStatus());
            p.setInt(6, s.getStaffId());
            return p.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteStaff(int id) throws SQLException {
        String sql = "DELETE FROM Staff WHERE staff_id=?";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, id);
            return p.executeUpdate() > 0;
        }
    }

    @Override
    public boolean activateStaff(int id) throws SQLException {
        return setStatus(id, "ACTIVE");
    }

    @Override
    public boolean deactivateStaff(int id) throws SQLException {
        return setStatus(id, "INACTIVE");
    }

    // Helper to toggle status
    private boolean setStatus(int id, String status) throws SQLException {
        String sql = "UPDATE Staff SET status=? WHERE staff_id=?";
        try (Connection c = getConn();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, status);
            p.setInt(2, id);
            return p.executeUpdate() > 0;
        }
    }

    // Map a ResultSet row to a Staff object
    private Staff mapRow(ResultSet r) throws SQLException {
        Staff s = new Staff();
        s.setStaffId(r.getInt("staff_id"));
        s.setName(r.getString("name"));
        s.setEmail(r.getString("email"));
        s.setPosition(r.getString("position"));
        s.setAddress(r.getString("address"));
        s.setStatus(r.getString("status"));
        return s;
    }
}
