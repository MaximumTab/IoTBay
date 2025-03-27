package com.iotbay.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable
{
    private int id;
    private String fname;
    private String lname;
    private String address;
    private int postalCode;
    private int phone;
    private int bsb;
    private int accNum;
    private String email;
    private String password;

    // Static list to hold users for the session
    private static List<Customer> users = new ArrayList<>();

    public Customer()
    {

    }

    public Customer(int id, String fname, String lname, String address, int postalCode, int phone, int bsb, int accNum, String email, String password)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.bsb = bsb;
        this.accNum = accNum;
        this.email = email;
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

    public int getPostalCode() { return postalCode; }
    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }

    public int getPhone() { return phone; }
    public void setPhone(int phone) { this.phone = phone; }

    public int getBsb() { return bsb; }
    public void setBsb(int bsb) { this.bsb = bsb; }

    public int getAccNum() { return accNum; }
    public void setAccNum(int accNum) { this.accNum = accNum; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public static List<Customer> getUsers() {
        return users;
    }

    // Add a user to the list
    public static void addUser(Customer user)
    {
        users.add(user);
    }

    //checking the users list for existing user based on the email
    public static boolean userExists(String email) {
        for (Customer user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    //checking if the users email and password match with any int the list of users
    public boolean isValidUser() {
        Customer validUser = findValidUser(this.email, this.password);
        if (validUser != null) {
            this.id = validUser.id;
            this.fname = validUser.fname;
            this.lname = validUser.lname;
            this.address = validUser.address;
            this.postalCode = validUser.postalCode;
            this.phone = validUser.phone;
            this.bsb = validUser.bsb;
            this.accNum = validUser.accNum;
            return true;
        }
        return false;
    }




}
