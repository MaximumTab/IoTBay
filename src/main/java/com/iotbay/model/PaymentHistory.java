package com.iotbay.model;

import java.io.Serializable;

public class PaymentHistory implements Serializable {

    private int paymentId;
    private String paymentCardNumber;
    private String paymentAmount;
    private String paymentDate;


    public PaymentHistory(String paymentCardNumber, String paymentAmount, String paymentDate) {
        this.paymentCardNumber = paymentCardNumber;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public PaymentHistory(int paymentId, String paymentCardNumber, String paymentAmount, String paymentDate) {
        this.paymentId = paymentId;
        this.paymentCardNumber = paymentCardNumber;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
        // getter  methods
    public int getPaymentId() {
        return paymentId;
    }

    public String getPaymentCardNumber() {
        return paymentCardNumber;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }



    //Setter methods

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentCardNumber(String paymentCardNumber) {
        this.paymentCardNumber = paymentCardNumber;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

}
