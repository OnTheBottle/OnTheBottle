package com.bottle.gateway.rest;

import com.bottle.gateway.codec.ReportCodec;
import com.bottle.properties.conf.WebApp;
import com.bottle.gateway.dto.ReportsWrapper;
import com.bottle.properties.OnTheBottle;
import com.bottle.properties.Verticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.bottle.gateway.rest.controller.ReportController;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
@Verticle
public class RestVerticle extends AbstractVerticle {

    //TODO: remove context reference
    private ApplicationContext context;
    private OnTheBottle onTheBottle;
    private WebApp webAppProperties;

    /**
     * Default constructor for unit test only
     */
    public RestVerticle() {
    }

    @Autowired
    public RestVerticle(ApplicationContext context, OnTheBottle onTheBottle, WebApp webAppProperties) {
        this.context = context;
        this.onTheBottle = onTheBottle;
        this.webAppProperties = webAppProperties;
    }

    /**
     * Run REST API
     */
    @Override
    public void start() throws Exception {
        Router mainRouter = Router.router(vertx);
        mainRouter.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedHeader("Authorization")
                .allowedHeader("Content-Type"));

        mainRouter.route().path("/").handler(rc -> {
            rc.response().end("zero page"); // that default functionality can be changed later !
        });

        // no correct route/URI was found in mainRouter - return 404
        mainRouter.route().last().handler( (RoutingContext context) -> {
            log.error("Error 404 is logged on mainRouter, URL = '{}', method = '{}', uri params = {}",
                    context.request().path(),
                    context.request().method(),
                    context.request().params() != null && !context.request().params().isEmpty() ?
                            context.request().params().toString() : "null");
            context.response().setStatusCode(404).end();
        });


        vertx.eventBus().registerDefaultCodec(ReportsWrapper.class, new ReportCodec());

        //TODO: refactor Controllers to Spring Components
        // register handlers for Login REST
        new ReportController(mainRouter, vertx);


        HttpServerOptions httpServerOptions = new HttpServerOptions();

        vertx.createHttpServer(httpServerOptions).requestHandler(mainRouter::accept)
                .listen(webAppProperties.getRest().getHttp().getPort());
    }

}