package com.motaharinia.batch.presentation.creditcard;


import java.math.BigDecimal;
import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-02 <br>
 * Time: 22:43:57 <br>
 * Description: <br>
 */
public class CreditCardModel {

    /**
     * شناسه
     */
    private Integer id;

    /**
     * شماره کارت
     */
    private String cardNumber;

    /**
     * تاریخ آخرین پرداخت
     */
    private Date lastPay;

    /**
     * مبلغ مانده
     */
    private BigDecimal remainAmount;

    public CreditCardModel() {
    }

    public CreditCardModel(String cardNumber, Date lastPay, BigDecimal remainAmount) {
        this.cardNumber = cardNumber;
        this.lastPay = lastPay;
        this.remainAmount = remainAmount;
    }

    @Override
    public String toString() {
        return "CreditCardModel{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", lastPay=" + lastPay +
                ", remainAmount=" + remainAmount +
                '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getLastPay() {
        return lastPay;
    }

    public void setLastPay(Date lastPay) {
        this.lastPay = lastPay;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }
}
