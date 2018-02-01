package com.bottle.gateway.rest.controller;

import com.bottle.gateway.dto.ReportsWrapper;
import com.bottle.gateway.rest.exceptions.ErrorHandler;
import com.bottle.gateway.rest.exceptions.ResponseExceptionFunction;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import com.bottle.gateway.rest.exceptions.ResponseException;

import static com.bottle.properties.utils.JsonFields.ERROR;
import static com.bottle.properties.utils.JsonFields.ERROR_CODE;
import static com.bottle.properties.utils.JsonFields.SUCCESS;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
public abstract class AbstractBaseController {

    private Vertx vertx;

    protected AbstractBaseController(Router router, String rootPath, Vertx vertx) {
        router.route(rootPath).handler(BodyHandler.create());
        router.exceptionHandler(t -> log.error("Exception: {}", t.getMessage()));
        this.vertx = vertx;
    }

    protected void handleRequest(RoutingContext rc, String eventBusQueueName, ResponseExceptionFunction<JsonObject, JsonObject> function) {
 /*       InteractionProcessor interactionProcessor = new InteractionProcessor(rc.getBodyAsString());
        Interaction interaction = interactionProcessor.parseNewInteractionJson();
        if (interactionProcessor.getErrorMessage() != null) {
            rc.response().setStatusCode(400).putHeader("content-type", "application/json")
                    .end(Json.encodePrettily(new JsonObject().put(ERROR, new JsonObject().put(MESSAGE,
                            interactionProcessor.getErrorMessage()))));
        } else {
            interaction.setTypeId(iType.getId());*/
            doSend(rc, eventBusQueueName, new ReportsWrapper(), function);
      //  }
    }

    /**
     * Send Interaction to the event bus and create the numessage from appropriate vertical
     */
    private void doSend(RoutingContext rc, String eventBusQueueName, ReportsWrapper reportsWrapper,
                        ResponseExceptionFunction<JsonObject, JsonObject> function) {
        vertx.eventBus().send(eventBusQueueName, reportsWrapper, new ErrorHandler(rc, jsonObject -> {
            // success handler
            if (function != null) {
                try {
                    jsonObject = function.apply(jsonObject);
                } catch (ResponseException e) {
                    rc.response().putHeader("content-type", "application/json").setStatusCode(e
                            .getErrorCode()).end(Json.encodePrettily(new JsonObject().put(ERROR, e.getMessage()
                    ).put(ERROR_CODE, e.getBottleErrorCode())));
                    return;
                }
            }
            rc.response().putHeader("content-type", "application/json").end(Json.encodePrettily(new
                    JsonObject().put(SUCCESS, jsonObject)));
        }));
    }

    /**
     * Unique name for identifying message queue
     * Style code for specifying name: 'internal.${model_name}'
     * 'internal' - means that it used for creating queue(s) between two verticles in nuweb core
     * '${model_name}' - for reflecting interaction CObject which will be send
     * Example: 'internal.persons'
     *
     * @return queue name
     */
    protected abstract String getEventBusQueueName();
}