/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tworope.reactor.refund.dto;

import java.util.Date;

/**
 *
 * @author tobah
 */
public class RefundDTO {
    
    private Date refundDate;
    private double refundAmount;
    private String refundOption;
    private String bankDetails;
    private String cardDetails;
    private String cashDetails;

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundOption() {
        return refundOption;
    }

    public void setRefundOption(String refundOption) {
        this.refundOption = refundOption;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public String getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getCashDetails() {
        return cashDetails;
    }

    public void setCashDetails(String cashDetails) {
        this.cashDetails = cashDetails;
    }
    
    
    
}
