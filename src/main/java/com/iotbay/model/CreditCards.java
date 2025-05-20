package com.iotbay.model;

import java.io.Serializable;

public class CreditCards implements Serializable {

    private int cardId;
    private String creditCardNumber;
    private String ccv;
    private String bsb;


    public CreditCards(String creditCardNumber, String ccv, String bsb) {
        this.creditCardNumber = creditCardNumber;
        this.ccv = ccv;
        this.bsb = bsb;
    }

    public CreditCards ( int cardId, String creditCardNumber, String ccv, String bsb )
    {
        this.cardId = cardId;
      this.creditCardNumber = creditCardNumber;
      this.ccv = ccv;
      this.bsb = bsb;
    }

    //Getter Methods
    public int getCardId() { return cardId; }
    public String getCreditCardNumber() { return creditCardNumber; }
    public String getCcv() { return ccv; }
    public String getBsb() { return bsb; }

    //Setter Methods
    public void setCardId(int cardId) { this.cardId = cardId; }
    public void setCreditCardNumber(String creditCardNumber) { this.creditCardNumber = creditCardNumber; }
    public void setCcv(String ccv) { this.ccv = ccv; }
    public void setBsb(String bsb) { this.bsb = bsb; }
}
