package com.motaharinia.batch.persistence.orm.creditcard;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2021-01-02 <br>
 * Time: 23:53:02 <br>
 * Description: <br>
 *     کلاس انتیتی کارت اعتباری
 */
@Entity
@Table(name = "credit_card", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"card_number"})})
public class CreditCard {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * شماره کارت
     */
    @Column(name = "card_number")
    private String cardNumber;

    /**
     * تاریخ آخرین پرداخت
     */
    @Column(name = "last_pay")
    private Date lastPay;

    /**
     *مبلغ مانده
     */
    @Column(name = "remain_amount")
    private BigDecimal remainAmount;

    @Override
    public String toString() {
        return "CreditCard{" +
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
