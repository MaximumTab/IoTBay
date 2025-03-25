package com.iotbay.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private int id;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;

    // Static list to hold users for the session
    private static List<Customer> users = new ArrayList<>();

    public Customer()
    {

    }

    public Customer(int id, String fname, String lname, String address, String phone, String email, String username, String password)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    // Getter and Setter methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static List<Customer> getUsers() {
        return users;
    }

    public void saveUser() {
        users.add(this);  // Save the current customer object
    }

    public boolean userExists(String email) {
        for (Customer user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidUser() {
        for (Customer user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
