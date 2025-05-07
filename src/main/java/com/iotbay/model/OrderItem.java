package com.iotbay.model;

import java.io.Serializable;

public class OrderItem implements Serializable
{
    int itemId;
    Order order;
    Devices device;
    int quantity;

    public OrderItem(int itemId, Order order, Devices device, int quantity)
    {
        this.itemId = itemId;
        this.order = order;
        this.device = device;
        this.quantity = quantity;
    }

    public int getItemId(){return itemId;}
    public Order getOrder(){return order;}
    public Devices getDevice(){return device;}
    public int getQuantity(){return quantity;}

    public void setItemId(int itemId){this.itemId = itemId;}
    public void setOrder(Order order){this.order = order;}
    public void setDevice(Devices device){this.device = device;}
    public void setQuantity(int quantity){this.quantity = quantity;}
}
