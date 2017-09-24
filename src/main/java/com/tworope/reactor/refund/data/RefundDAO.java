/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tworope.reactor.refund.data;

import com.orientechnologies.orient.core.id.ORecordId;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.modules.javahandler.JavaHandlerModule;
import com.tworope.reactor.refund.dto.RefundDTO;
import com.tworope.reactor.refund.frames.Refund;
import com.tworope.reactor.refund.util.ReactiveFrame;

/**
 *
 * @author tobah
 */
public class RefundDAO {

    public boolean saveRefund(RefundDTO refundDTO) {
        OrientGraphFactory factory = new ReactiveFrame().getOrientGraphFactory();
        FramedGraph<OrientGraph> framedGraph = null;
        try {

            OrientGraph graph = factory.getTx();
            framedGraph = new FramedGraphFactory(new JavaHandlerModule()).create(graph);

            Refund refund = framedGraph.addVertex("class:Refund", Refund.class);

            refund.setRefundDate(refundDTO.getRefundDate());
            refund.setRefundAmount(refundDTO.getRefundAmount());
            refund.setRefundOption(refundDTO.getRefundOption());
            refund.setBankDetails(refundDTO.getBankDetails());
            refund.setCardDetails(refundDTO.getCardDetails());
            refund.setCashDetails(refundDTO.getCashDetails());

            graph.commit();

            System.out.println("Refund id before save " + refund.asVertex().getId());

            return true;

        } catch (Exception e) {
            System.out.println("exception to add Refund " + e);
        } finally {
            if (framedGraph != null) {
                framedGraph.shutdown();
            }
        }
        return false;

    }

    public RefundDTO getAllRefunds() {

        OrientGraphFactory factory = new ReactiveFrame().getOrientGraphFactory();
        FramedGraph<OrientGraph> framedGraph = null;
        RefundDTO refundDTO = null;

        try {

            OrientGraph graph = factory.getTx();
            framedGraph = new FramedGraphFactory(new JavaHandlerModule()).create(graph);

            ORecordId orid = new ORecordId("#37:0");

            Refund refund = framedGraph.getVertex(orid, Refund.class);

            refundDTO = saveRefundDTO(refund);

            System.out.println("Refund details " + refundDTO);

        } catch (Exception e) {
            System.out.println("exception to retrieve Refund " + e);
        } finally {
            if (framedGraph != null) {
                framedGraph.shutdown();
            }
        }
        return refundDTO;
    }

    public static RefundDTO saveRefundDTO(Refund refund) {

        RefundDTO refundDTO = new RefundDTO();
        
        refundDTO.setRefundDate(refund.getRefundDate());
        refundDTO.setRefundAmount(refund.getRefundAmount());
        refundDTO.setRefundOption(refund.getRefundOption());
        refundDTO.setBankDetails(refund.getBankDetails());
        refundDTO.setCardDetails(refund.getCardDetails());
        refundDTO.setCashDetails(refund.getCashDetails());

        return refundDTO;
    }
}
