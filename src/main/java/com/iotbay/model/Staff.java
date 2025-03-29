package com.iotbay.model;

import java.io.Serializable;

public class Staff implements Serializable
{
    private int Staffid;
    private String fname;
    private String lname;
    private String email;
    private String phone;

    public Staff()
    {

    }

    public Staff(int Staffid, String fname, String lname, String email, String phone)
    {
        this.Staffid = Staffid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    public int getStaffid() {return Staffid;}
    public void setStaffid(int Staffid) {this.Staffid = Staffid;}

    public String getFname() {return fname;}
    public void setFname(String fname) {this.fname = fname;}

    public String getLname() {return lname;}
    public void setLname(String lname) {this.lname = lname;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
}
