package com.iotbay.model;

import java.io.Serializable;

public class Order implements Serializable
{
    private int orderID;
    private int orderDate;
    private String orderStatus;
    private double orderAmount;

    public Order()
    {

    }

    public Order (int orderID, int orderDate, String orderStatus, double orderAmount)
    {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
    }

    //getter and setter methods
    public int getOrderID() {return orderID;}
    public void setOrderID(int orderID) {this.orderID = orderID;}

    public int getOrderDate() {return orderDate;}
    public void setOrderDate(int orderDate) {this.orderDate = orderDate;}

    public String getOrderStatus() {return orderStatus;}
    public void setOrderStatus(String orderStatus) {this.orderStatus = orderStatus;}

    public double getOrderAmount() {return orderAmount;}
    public void setOrderAmount(double orderAmount) {this.orderAmount = orderAmount;}
}
