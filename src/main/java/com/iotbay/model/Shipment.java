package com.iotbay.model;

import java.io.Serializable;

public class Shipment implements Serializable
{
    private int shipmentId;
    private int trackingId;
    private String shipmentAddress;
    private String shipmentType;
    private int shipmentDate;

    public Shipment()
    {

    }

    public Shipment(int shipmentId, int trackingId, String shipmentAddress, String shipmentType, int shipmentDate)
    {
        this.shipmentId = shipmentId;
        this.trackingId = trackingId;
        this.shipmentAddress = shipmentAddress;
        this.shipmentType = shipmentType;
        this.shipmentDate = shipmentDate;
    }

    //Getter and setter methods
    public int getShipmentId() {return shipmentId;}
    public void setShipmentId(int shipmentId) {this.shipmentId = shipmentId;}

    public int getTrackingId() {return trackingId;}
    public void setTrackingId(int trackingId) {this.trackingId = trackingId;}

    public String getShipmentAddress() {return shipmentAddress;}
    public void setShipmentAddress(String shipmentAddress) {this.shipmentAddress = shipmentAddress;}

    public String getShipmentType() {return shipmentType;}
    public void setShipmentType(String shipmentType) {this.shipmentType = shipmentType;}

    public int getShipmentDate() {return shipmentDate;}
    public void setShipmentDate(int shipmentDate) {this.shipmentDate = shipmentDate;}
}
