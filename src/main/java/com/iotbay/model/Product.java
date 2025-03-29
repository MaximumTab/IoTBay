package com.iotbay.model;

import java.io.Serializable;

public class Product implements Serializable
{
    private int productID;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productSupplier;
    private String productCategory;
    private int productStock;

    public Product()
    {

    }

    public Product(int productID, String productName, String productDescription, double productPrice, String productSupplier, String productCategory, int productStock)
    {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productSupplier = productSupplier;
        this.productCategory = productCategory;
        this.productStock = 0;
    }

    //Getter and setter methods
    public int getProductID() {return productID;}
    public void setProductID(int productID) {this.productID = productID;}

    public String getProductName() {return productName;}
    public void setProductName(String productName) {this.productName = productName;}

    public String getProductDescription() {return productDescription;}
    public void setProductDescription(String productDescription) {this.productDescription = productDescription;}

    public double getProductPrice() {return productPrice;}
    public void setProductPrice(double productPrice) {this.productPrice = productPrice;}

    public String getProductSupplier() {return productSupplier;}
    public void setProductSupplier(String productSupplier) {this.productSupplier = productSupplier;}

    public String getProductCategory() {return productCategory;}
    public void setProductCategory(String productCategory) {this.productCategory = productCategory;}

    public int getProductStock() {return productStock;}
    public void setProductStock(int productStock) {this.productStock = productStock;}
}
