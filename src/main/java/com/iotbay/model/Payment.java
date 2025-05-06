package com.iotbay.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable
{
    int paymentId;
    Order order;
    String paymentMethod;
    String creditCard;
    Double amount;
    LocalDateTime paymentDate;
    String paymentStatus; //saved || finalized

    public Payment(int paymentId, Order order, String paymentMethod, String creditCard, Double amount, LocalDateTime paymetnDate)
    {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.creditCard = creditCard;
        this.amount = amount;
        this.paymentDate = paymetnDate;
    }

    public int getPaymentId() {return paymentId;}
    public Order getOrder() {return order;}
    public String getPaymentMethod() {return paymentMethod;}
    public String getCreditCard() {return creditCard;}
    public Double getAmount() {return amount;}
    public LocalDateTime getPaymentDate() {return paymentDate;}
    public String getPaymentStatus() {return paymentStatus;}

    public void setPaymentStatus(String paymentStatus) {this.paymentStatus = paymentStatus;}
    public void setPaymentId(int paymentId) {this.paymentId = paymentId;}
    public void setOrder(Order order) {this.order = order;}
    public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
    public void setCreditCard(String creditCard) {this.creditCard = creditCard;}
    public void setAmount(Double amount) {this.amount = amount;}
    public void setPaymentDate(LocalDateTime paymentDate) {this.paymentDate = paymentDate;}

}
