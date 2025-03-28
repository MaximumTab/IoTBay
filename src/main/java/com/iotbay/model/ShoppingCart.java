package com.iotbay.model;

import java.io.Serializable;


public class ShoppingCart implements Serializable
{
    private int cartID;
    private double productPrices;
    private String productNum;

    public ShoppingCart()
    {

    }

    public ShoppingCart(int cartID, double productPrices, String productNum)
    {
        this.cartID = cartID;
        this.productPrices = productPrices;
        this.productNum = productNum;
    }

    //Getter and setter methods
    public int getCartID() {return cartID;}
    public void setCartID(int cartID) {this.cartID = cartID;}

    public double getProductPrices() {return productPrices;}
    public void setProductPrices(double productPrices) {this.productPrices = productPrices;}

    public String getProductNum() {return productNum;}
    public void setProductNum(String productNum) {this.productNum = productNum;}

}


