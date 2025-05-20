package com.iotbay.model;

import java.io.Serializable;

public class Devices implements Serializable
{
<<<<<<< HEAD
=======
    Integer createdByUserId;
>>>>>>> a21e133 (Initial commit from IntelliJ)
    int deviceId;
    String deviceName;
    String deviceType;
    double devicePrice;
    int deviceQuantity;
<<<<<<< HEAD
    User deviceCreator;

    public Devices(int deviceId, String deviceName, String deviceType, double devicePrice, int deviceQuantity, User deviceCreator)
=======


    public Devices(int deviceId, String deviceName, String deviceType, double devicePrice, int deviceQuantity, Integer createdByUserId)
>>>>>>> a21e133 (Initial commit from IntelliJ)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.devicePrice = devicePrice;
        this.deviceQuantity = deviceQuantity;
<<<<<<< HEAD
        this.deviceCreator = deviceCreator;
=======
        this.createdByUserId = createdByUserId;
>>>>>>> a21e133 (Initial commit from IntelliJ)
    }

    public int getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getDeviceType() { return deviceType; }
    public double getDevicePrice() { return devicePrice; }
    public int getDeviceQuantity() { return deviceQuantity; }
<<<<<<< HEAD
    public User getDeviceCreator() { return deviceCreator; }
=======
    public Integer getCreatedByUserId() {return createdByUserId;}
>>>>>>> a21e133 (Initial commit from IntelliJ)

    public void setDeviceId(int deviceId) { this.deviceId = deviceId; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public void setDevicePrice(double devicePrice) { this.devicePrice = devicePrice; }
    public void setDeviceQuantity(int deviceQuantity) { this.deviceQuantity = deviceQuantity; }
<<<<<<< HEAD
    public void setDeviceCreator(User deviceCreator) { this.deviceCreator = deviceCreator; }
=======
    public void setCreatedByUserId(Integer createdByUserId) {this.createdByUserId = createdByUserId;}
>>>>>>> a21e133 (Initial commit from IntelliJ)
}
