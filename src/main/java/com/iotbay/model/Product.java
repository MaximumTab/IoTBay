package com.iotbay.model;

import java.io.Serializable;

public class Product implements Serializable
{
    private int productID;
    private String productName;
    private String productDescription;
    private int productPrice;
    private String productImage;
    private String productCategory;
    private String productType;

    public Product()
    {

    }

    public Product(int productID, String productName, String productDescription, int productPrice, String productImage, String productCategory)
    {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;

    }

    private int getProductID()
    {
        return productID;
    }


}
