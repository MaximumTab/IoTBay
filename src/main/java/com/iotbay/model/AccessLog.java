package com.iotbay.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AccessLog implements Serializable {

    private int logId;
    User user;
    LocalDateTime logInTime;
    LocalDateTime logOutTime;

    public AccessLog (int logId, User user, LocalDateTime logInTime, LocalDateTime logOutTime)
    {
        this.logId = logId;
        this.user = user;
        this.logInTime = logInTime;
        this.logOutTime = logOutTime;
    }

    //Getter Methods
    public int getLogId() { return logId; }
    public User getUser() { return user; }
    public LocalDateTime getLogInTime() { return logInTime; }
    public LocalDateTime getLogOutTime() { return logOutTime; }

    //Setter Methods
    public void setLogId(int logId) { this.logId = logId; }
    public void setUser(User user) { this.user = user; }
    public void setLogInTime(LocalDateTime logInTime) { this.logInTime = logInTime; }
    public void setLogOutTime(LocalDateTime logOutTime) { this.logOutTime = logOutTime; }

}
