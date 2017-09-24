/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tworope.reactor.refund.frames;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;
import java.util.Date;

/**
 *
 * @author tobah
 */
public interface Refund extends VertexFrame{
    
    @Property("refund_date")
    public Date getRefundDate();
    
    @Property("refund_date")
    public void  setRefundDate(Date refundDate);
    
    @Property("refund_amount")
    public double getRefundAmount();
    
    @Property("refund_amount")
    public void  setRefundAmount(double refundAmount);
    
    @Property("refund_option")
    public String getRefundOption();
    
    @Property("refund_option")
    public void  setRefundOption(String refundOption);
    
    @Property("bank_details")
    public String getBankDetails();
    
    @Property("bank_details")
    public void  setBankDetails(String bankDetails);
    
    @Property("card_details")
    public String getCardDetails();
    
    @Property("card_details")
    public void  setCardDetails(String cardDetails);
    
    @Property("cash_details")
    public String getCashDetails();
    
    @Property("cash_details")
    public void  setCashDetails(String cashDetails);
}
