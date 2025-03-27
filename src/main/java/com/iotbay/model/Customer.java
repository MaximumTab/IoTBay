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

    //retrive the number of users
    public static int getNumUsers()
    {
        return users.size();
    }

    // Add a user to the list
    public static void addUser(Customer user)
    {
        users.add(user);
    }

    public static void removeUser(Customer user)
    {
        users.remove(user);
    }

    // Checking if the user's email and password match any in the list of users
    public boolean authenticateUser(String email, String password)
    {
        for (Customer user : users)
        {
            if (user.getEmail().equals(email))
            {
                return user.getPassword().equals(password);
            }
        }
        return false;
    }

}
