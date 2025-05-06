package com.iotbay.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable
{
    private int orderID;
    User orderCustomer;
    LocalDateTime orderDate;
    String orderStatus; //saved || submitted || canceled
    List<OrderItem> items;

    public Order(User orderCustomer, int orderID, LocalDateTime orderDate, String orderStatus, List<OrderItem> items)
    {
        this.orderCustomer = orderCustomer;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.items = items;
    }

    public int getOrderID() {return orderID;}
    public User getOrderCustomer() {return orderCustomer;}
    public LocalDateTime getOrderDate() {return orderDate;}
    public String getOrderStatus() {return orderStatus;}
    public List<OrderItem> getItems() {return items;}

    public void setOrderID(User orderCustomer) {this.orderID = orderID;}
    public void setOrderCustomer(User orderCustomer) {this.orderCustomer = orderCustomer;}
    public void setOrderDate(LocalDateTime orderDate) {this.orderDate = orderDate;}
    public void setOrderStatus(String orderStatus) {this.orderStatus = orderStatus;}
    public void setItems(List<OrderItem> items) {this.items = items;}

}
