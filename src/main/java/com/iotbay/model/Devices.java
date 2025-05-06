package com.iotbay.model;

import java.io.Serializable;

public class Devices implements Serializable
{
    int deviceId;
    String deviceName;
    String deviceType;
    double devicePrice;
    int deviceQuantity;
    User deviceCreator;

    public Devices(int deviceId, String deviceName, String deviceType, double devicePrice, int deviceQuantity, User deviceCreator)
    {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.devicePrice = devicePrice;
        this.deviceQuantity = deviceQuantity;
        this.deviceCreator = deviceCreator;
    }

    public int getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getDeviceType() { return deviceType; }
    public double getDevicePrice() { return devicePrice; }
    public int getDeviceQuantity() { return deviceQuantity; }
    public User getDeviceCreator() { return deviceCreator; }

    public void setDeviceId(int deviceId) { this.deviceId = deviceId; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public void setDevicePrice(double devicePrice) { this.devicePrice = devicePrice; }
    public void setDeviceQuantity(int deviceQuantity) { this.deviceQuantity = deviceQuantity; }
    public void setDeviceCreator(User deviceCreator) { this.deviceCreator = deviceCreator; }
}
