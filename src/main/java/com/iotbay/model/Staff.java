package com.iotbay.model;

public class Staff {
    private int    staffId;
    private String name;
    private String email;
    private String position;
    private String address;
    private String status;  // "ACTIVE" or "INACTIVE"

    // staffId
    public int getStaffId()             { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }

    // name
    public String getName()             { return name; }
    public void setName(String name)    { this.name = name; }

    // email
    public String getEmail()            { return email; }
    public void setEmail(String email)  { this.email = email; }

    // position
    public String getPosition()             { return position; }
    public void setPosition(String position){ this.position = position; }

    // address
    public String getAddress()               { return address; }
    public void setAddress(String address)   { this.address = address; }

    // status
    public String getStatus()                { return status; }
    public void setStatus(String status)     { this.status = status; }
}
