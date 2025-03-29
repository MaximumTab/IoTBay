package com.iotbay.model;

import java.io.Serializable;

public class ProductCategory implements Serializable
{
    private int CategoryID;
    private String CategoryName;
    private String CategoryDesc;

    public ProductCategory()
    {

    }

    public ProductCategory(int CategoryID, String CategoryName, String CategoryDesc)
    {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.CategoryDesc = CategoryDesc;
    }

    //getter and setter methods
    public int getCategoryID() {return CategoryID;}
    public void setCategoryID(int CategoryID) {this.CategoryID = CategoryID;}

    public String getCategoryName() {return CategoryName;}
    public void setCategoryName(String CategoryName) {this.CategoryName = CategoryName;}

    public String getCategoryDesc() {return CategoryDesc;}
    public void setCategoryDesc(String CategoryDesc) {this.CategoryDesc = CategoryDesc;}
}
