package com.iotbay.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable {

    private String fname;
    private String lname;
    private String address;
    private int postalCode;
    private int bsb;
    private int accNum;
    private String password;  // local un-hashed password for legacy login

    private static List<Customer> allCustomers = new ArrayList<>();

    // No-arg constructor
    public Customer() {
        super(0, 0, "", "", "", "", "customer", "1");
    }

    // Full constructor
    public Customer(int id, String fname, String lname, String address, int postalCode, String phone, int bsb, int accNum, String email, String password) {
        super(id, 0, fname + " " + lname, email, password, phone, "customer", "1");
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.postalCode = postalCode;
        this.bsb = bsb;
        this.accNum = accNum;
        this.password = password;
    }

    // Setters
    public void setId(int id) { super.setId(id); }
    public void setFname(String fname) { this.fname = fname; }
    public void setLname(String lname) { this.lname = lname; }
    public void setAddress(String address) { this.address = address; }
    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }
    public void setBsb(int bsb) { this.bsb = bsb; }
    public void setAccNum(int accNum) { this.accNum = accNum; }
    public void setPassword(String password) { this.password = password; }

    // Getters
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getAddress() { return address; }
    public int getPostalCode() { return postalCode; }
    public int getBsb() { return bsb; }
    public int getAccNum() { return accNum; }

    // Authentication logic
    public boolean authenticateUser(String email, String password) {
        return this.getEmail().equalsIgnoreCase(email) && this.password.equals(password);
    }

    // --- Static methods for online user tracking ---

    public static void addOnlineUser(Customer c) {
        if (c != null && !allCustomers.contains(c)) {
            allCustomers.add(c);
        }
    }

    public static void removeOnlineUser(Customer c) {
        allCustomers.remove(c);
    }

    public static int getNumOnlineUsers() {
        return allCustomers.size();
    }

    // Optional: for admin cleanup/testing
    public static void clearOnlineUsers() {
        allCustomers.clear();
    }

    public static List<Customer> getOnlineUsers() {
        return new ArrayList<>(allCustomers); // return copy for safety
    }
}