/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tworope.reactor.refund;

import com.tworope.reactor.refund.data.RefundDAO;
import com.tworope.reactor.refund.dto.RefundDTO;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author tobah
 */
public class RefundVerticle extends AbstractVerticle {

    private final static Logger LOGGER = Logger.getLogger(RefundVerticle.class.getName());

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String CONTENT_TYPE_TEXT = "content-type";

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RefundVerticle());
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        startHTTPServer();
    }

    private Future<Void> startHTTPServer() {
        Future<Void> future = Future.future();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedHeader("Content-Type"));
        router.route().handler(BodyHandler.create());

        router.get("/refund").handler(this::homeRoute);
        router.post("/refund").handler(this::createRefundRoute);

        server.requestHandler(router::accept).listen(8080, ar -> {
            if (ar.succeeded()) {
                LOGGER.info("Web Server started");
                future.complete();
            } else {
                LOGGER.info("Server failed to start");
                future.fail(ar.cause());
            }
        });

        return future;
    }

    private void homeRoute(RoutingContext context) {

        vertx.<RefundDTO>executeBlocking(future -> {
            RefundDTO refundDTO = null;
            try {
                RefundDAO refundDAO = new RefundDAO();
                refundDTO = refundDAO.getAllRefunds();
                System.out.println("Same block code goes here");
            } catch (Exception e) {
                System.out.println("Error occurred " + e);
            }
            future.complete(refundDTO);
        }, response -> {
            if (response.succeeded()) {
                context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                        .end(Json.encodePrettily(new JsonObject().put("success", response.result())));
            } else {
                context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                        .end(Json.encodePrettily(new JsonObject().put("error", response.cause())));
                System.out.println("Something happened " + response.cause());
            }
        });

    }

    private void createRefundRoute(RoutingContext context) {

        vertx.executeBlocking(future -> {
            
            RefundDTO refundDTO = new RefundDTO();
            
            refundDTO.setRefundDate(new Date());
            refundDTO.setRefundAmount(Double.parseDouble(context.request().getParam("refundAmount")));
            refundDTO.setRefundOption(context.request().getParam("refundOption"));
            refundDTO.setBankDetails(context.request().getParam("bankDetails"));
            refundDTO.setCardDetails(context.request().getParam("cardDetails"));
            refundDTO.setCashDetails(context.request().getParam("cashDetails"));
            
            try {
                
                RefundDAO refundDAO = new RefundDAO();
                refundDAO.saveRefund(refundDTO);

            } catch (Exception e) {
                System.out.println("Error occurred " + e);
            }
            future.complete();
        }, response -> {
            if (response.succeeded()) {
                context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                        .end(Json.encodePrettily(response.result()));
            } else {
                context.response().putHeader(CONTENT_TYPE_TEXT, JSON_CONTENT_TYPE)
                        .end(Json.encodePrettily(new JsonObject().put("error", response.cause())));
                System.out.println("Something happened " + response.cause());
            }
        });

    }
}
