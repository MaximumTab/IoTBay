package com.iotbay.model;

import java.io.Serializable;

public class StaffDetail implements Serializable
{
    int staffId;
    String staffPosition;
    String staffAddress;
    User staffUser;

    public StaffDetail (int staffId, String staffPosition, String staffAddress, User staffUser)
    {
        this.staffId = staffId;
        this.staffPosition = staffPosition;
        this.staffAddress = staffAddress;
        this.staffUser = staffUser;
    }

    public int getStaffId() { return staffId; }
    public String getStaffPosition() { return staffPosition; }
    public String getStaffAddress() { return staffAddress; }
    public User getStaffUser() { return staffUser; }

    public void setStaffId(int staffId) { this.staffId = staffId; }
    public void setStaffPosition(String staffPosition) { this.staffPosition = staffPosition; }
    public void setStaffAddress(String staffAddress) { this.staffAddress = staffAddress; }
    public void setStaffUser(User staffUser) { this.staffUser = staffUser; }
}
