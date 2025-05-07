package com.iotbay.model;

import java.io.Serializable;

public class Supplier implements Serializable
{
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierType; // company || individual
    private String supplierAddress;
    private Boolean supplierIsActive;

    public Supplier(int supplierId, String supplierName, String supplierEmail, String supplierType, Boolean supplierIsActive)
    {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierType = supplierType;
        this.supplierIsActive = supplierIsActive;
        this.supplierAddress = "";
    }

    public int getSupplierId() { return supplierId; }
    public String getSupplierName() { return supplierName; }
    public String getSupplierEmail() { return supplierEmail; }
    public String getSupplierType() { return supplierType; }
    public Boolean getSupplierIsActive() { return supplierIsActive; }
    public String getSupplierAddress() { return supplierAddress; }

    public void setSupplierId(int supplierId) { this.supplierId = supplierId; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public void setSupplierEmail(String supplierEmail) { this.supplierEmail = supplierEmail; }
    public void setSupplierType(String supplierType) { this.supplierType = supplierType; }
    public void setSupplierAddress (String supplierAddress) { this.supplierAddress = supplierAddress;}
}
