/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tworope.reactor.refund.test;

import com.tworope.reactor.refund.data.RefundDAO;
import com.tworope.reactor.refund.dto.RefundDTO;
import java.util.Date;

/**
 *
 * @author tobah
 */
public class Test {
    
    public static void main(String[] args) {
        
        Test test = new Test();
        RefundDAO refundDAO = new RefundDAO();
        refundDAO.saveRefund(test.createrefundDTO());
        
    }
    
    public RefundDTO createrefundDTO(){
        
        RefundDTO refundDTO = new RefundDTO();
        
        refundDTO.setRefundDate(new Date());
        refundDTO.setRefundAmount(509.90);
        refundDTO.setRefundOption("by card");
        refundDTO.setBankDetails("FNB");
        refundDTO.setCardDetails("Mastercard");
        refundDTO.setCashDetails("cash deposit");
        
        return refundDTO;
        
    }
}
