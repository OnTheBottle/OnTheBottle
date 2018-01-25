package com.bottle.gateway.rest.exceptions;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import static com.bottle.properties.utils.JsonFields.ERROR;
import static com.bottle.properties.utils.JsonFields.ERROR_MESSAGE;
import static com.bottle.properties.utils.JsonFields.MESSAGE;


/**
 * Created by Sergey on 26.01.2017.
 */
public class ErrorHandler implements Handler<AsyncResult<Message<Object>>> {

    public static final String CONTENT_TYPE = "content-type";
    public static final String APPLICATION_JSON = "application/json";

    private RoutingContext routingContext;
    private Handler<JsonObject> successHandler;

    /**
     * Constructor with parameters
     *
     * @param routingContext context instance
     * @param successHandler handler instance
     */
    public ErrorHandler(RoutingContext routingContext, Handler<JsonObject> successHandler) {
        this.routingContext = routingContext;
        this.successHandler = successHandler;
    }

    @Override
    public void handle(AsyncResult<Message<Object>> result) {
        if (result.succeeded()) {
            if (result.result().body() instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) result.result().body();
                if (jsonObject.containsKey(ERROR_MESSAGE)) {
                    jsonObject.put(MESSAGE, jsonObject.getValue(ERROR_MESSAGE)).remove(ERROR_MESSAGE);
                    routingContext.response().setStatusCode(400).putHeader(CONTENT_TYPE, APPLICATION_JSON)
                            .end(Json.encodePrettily(new JsonObject().put(ERROR, jsonObject)));
                } else {
                    successHandler.handle(jsonObject);
                }
            } else {
                routingContext.response().setStatusCode(500).putHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .end(Json.encodePrettily(result.result().body()));
            }
        } else {
            routingContext.response().setStatusCode(500).putHeader(CONTENT_TYPE, APPLICATION_JSON)
                    .end(Json.encodePrettily(new JsonObject().put(ERROR, result.cause().getMessage())));
        }
    }

}