package com.iotbay.model;

import java.io.Serializable;

public class Payment implements Serializable
{
    private int paymentId;
    private String paymentName;
    private int paymentDate;
    private int recipientBsb;
    private int recipientAccNum;
    private double paymentAmount;

    public Payment()
    {

    }

    public Payment(int paymentId, String paymentName, int paymentDate, int recipientBsb, int recipientAccNum, double paymentAmount)
    {
        this.paymentId = paymentId;
        this.paymentName = paymentName;
        this.paymentDate = paymentDate;
        this.recipientBsb = recipientBsb;
        this.recipientAccNum = recipientAccNum;
        this.paymentAmount = paymentAmount;
    }

    //Getter and setter methods
    public int getPaymentId() {return paymentId;}
    public void setPaymentId(int paymentId) {this.paymentId = paymentId;}

    public String getPaymentName() {return paymentName;}
    public void setPaymentName(String paymentName) {this.paymentName = paymentName;}

    public int getPaymentDate() {return paymentDate;}
    public void setPaymentDate(int paymentDate) {this.paymentDate = paymentDate;}

    public int getRecipientBsb() {return recipientBsb;}
    public void setRecipientBsb(int recipientBsb) {this.recipientBsb = recipientBsb;}

    public int getRecipientAccNum() {return recipientAccNum;}
    public void setRecipientAccNum(int recipientAccNum) {this.recipientAccNum = recipientAccNum;}

    public double getPaymentAmount() {return paymentAmount;}
    public void setPaymentAmount(double paymentAmount) {this.paymentAmount = paymentAmount;}
}
