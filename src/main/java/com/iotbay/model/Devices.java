package com.iotbay.model;

import java.io.Serializable;

public class Devices implements Serializable
{
    Integer createdByUserId;
    int deviceId;
    String deviceName;
    String deviceType;
    double devicePrice;
    int deviceQuantity;


    public Devices(int deviceId, String deviceName, String deviceType, double devicePrice, int deviceQuantity, Integer createdByUserId)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.devicePrice = devicePrice;
        this.deviceQuantity = deviceQuantity;
        this.createdByUserId = createdByUserId;
    }

    public int getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getDeviceType() { return deviceType; }
    public double getDevicePrice() { return devicePrice; }
    public int getDeviceQuantity() { return deviceQuantity; }
    public Integer getCreatedByUserId() {return createdByUserId;}

    public void setDeviceId(int deviceId) { this.deviceId = deviceId; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public void setDevicePrice(double devicePrice) { this.devicePrice = devicePrice; }
    public void setDeviceQuantity(int deviceQuantity) { this.deviceQuantity = deviceQuantity; }
    public void setCreatedByUserId(Integer createdByUserId) {this.createdByUserId = createdByUserId;}
}
