package com.iotbay.model.dao;

import com.iotbay.model.Staff;
import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {

    int addStaff(Staff s) throws SQLException;

    Staff getStaffById(int id) throws SQLException;
    List<Staff> getAllStaff() throws SQLException;
    List<Staff> searchStaff(String namePattern, String positionPattern) throws SQLException;

    boolean updateStaff(Staff s) throws SQLException;
    boolean deleteStaff(int id) throws SQLException;
    boolean activateStaff(int id) throws SQLException;
    boolean deactivateStaff(int id) throws SQLException;
}
