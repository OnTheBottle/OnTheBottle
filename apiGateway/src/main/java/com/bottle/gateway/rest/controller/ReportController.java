package com.bottle.gateway.rest.controller;

import com.bottle.properties.utils.EventBuses;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

import static com.bottle.properties.utils.EventBuses.EB_GATEWAY;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
public class ReportController extends AbstractBaseController {

    public ReportController(Router router, Vertx vertx) {
        super(router, "/reports*", vertx);
        router.post("/reports").handler(this::readRelationship);
    }

    private void readRelationship(RoutingContext routingContext) {

        //   handleRequest(routingContext, EB_PREPROCESSOR, null);
        log.debug("Save logs");
    }


    @Override
    protected String getEventBusQueueName() {
        return EB_GATEWAY;
    }
}
