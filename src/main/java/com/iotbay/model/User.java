package com.iotbay.model;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private int cardId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String phone;
    private String userType;
    private String isActive;

    public User(int userId, int cardId, String fullName, String email, String passwordHash, String phone, String userType, String isActive) {
        this.userId = userId;
        this.cardId = cardId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phone = phone;
        this.userType = userType;
        this.isActive = isActive;
    }

    // Getters
    public int getUserId() { return userId; }
    public int getCardId() { return cardId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getPhone() { return phone; }
    public String getUserType() { return userType; }
    public String getIsActive() { return isActive; }

    // Setters
    public void setId(int userId) { this.userId = userId; } // <== THIS is required for Customer
    public void setCardId(int cardId) { this.cardId = cardId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setIsActive(String isActive) { this.isActive = isActive; }
}
