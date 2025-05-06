package com.iotbay.model;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phone;
    private String userType; // customer || staff
    private String isActive;

    public User (int userId, String fullName, String email, String passwordHash, String phone, String userType, String isActive)
    {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.userType = userType;
        this.isActive = isActive;
    }

    //Getter Methods
    public int getId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash () { return passwordHash; }
    public String getPhone() { return phone; }
    public String getUserType() { return userType; }
    public String getIsActive() { return isActive; }

    //Setter Methods
    public void setId(int id) { userId = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setIsActive(String isActive) { this.isActive = isActive; }

}
