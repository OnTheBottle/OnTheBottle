package com.bottle.gateway.conf;

import com.bottle.properties.Verticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
public class VerticleDeployer {
    private ApplicationContext applicationContext;
    private Vertx vertx;
    private SpringVerticleFactory springVerticleFactory;
    private OptionsConfigurer optionsConfigurer;
    private DeploymentOptions commonVerticleDeploymentOptions;

    @Autowired
    public VerticleDeployer(ApplicationContext applicationContext,
                            Vertx vertx,
                            SpringVerticleFactory springVerticleFactory,
                            OptionsConfigurer optionsConfigurer,
                            DeploymentOptions commonVerticleDeploymentOptions) {
        this.applicationContext = applicationContext;
        this.vertx = vertx;
        this.springVerticleFactory = springVerticleFactory;
        this.optionsConfigurer = optionsConfigurer;
        this.commonVerticleDeploymentOptions = commonVerticleDeploymentOptions;
    }

    /**
     * Deploy verticles when the Spring application is ready.
     */
    @EventListener
    public void deployVerticles(ApplicationReadyEvent event) {
        log.info("commonVerticleDeploymentOptions: {}", commonVerticleDeploymentOptions.toJson());
        optionsConfigurer.configureCommonFromProperties(commonVerticleDeploymentOptions);
        log.info("commonVerticleDeploymentOptions after configuration from Environment: {}",
                commonVerticleDeploymentOptions.toJson());

        vertx.runOnContext(c -> {
            Future<Void> finalFuture = Future.future();
            log.info("Starting Verticles deployment...");
            log.info("Getting Verticle bean names...");

            String[] verticleBeanNames = applicationContext.getBeanNamesForAnnotation(Verticle.class);

            log.info(String.format("Found verticle names (%d): %s ",
                    verticleBeanNames.length, Arrays.toString(verticleBeanNames)));
            log.info("Starting deploying found verticles...");

            Future<Void> future = Future.succeededFuture();
            for (String verticleBeanName : verticleBeanNames) {
                future = future.compose(v -> deployVerticle(verticleBeanName));
            }

            future.compose(v -> finalFuture.complete(), finalFuture);

            finalFuture.setHandler(result -> {
                if (result.succeeded()) {
                    log.info("All Verticles have been deployed!");
                } else {
                    log.error("Ohh Crap!", result.cause());
                }
            });
        });
    }

    private Future<Void> deployVerticle(String verticleBeanName) {
        log.info("Deploying {}...", verticleBeanName);

        Future<Void> deploymentFuture = Future.future();
        vertx.deployVerticle(springVerticleFactory.prefix() + ":" + verticleBeanName,
                getDeploymentOptions(verticleBeanName), result -> {
                    if (result.succeeded()) {
                        log.info(String.format("Verticle '%s' has been deployed successfully",
                                verticleBeanName
                        ));
                        deploymentFuture.complete();
                    } else {
                        String errorMessage = String.format("Verticle '%s' has not been deployed!",
                                verticleBeanName
                        );
                        log.error(errorMessage, result.cause());
                        vertx.close(v -> log.error("Vert.x has been closed!"));
                        deploymentFuture.fail(errorMessage);
                    }
                }
        );

        return deploymentFuture;
    }

    private DeploymentOptions getDeploymentOptions(String beanName) {
        log.info(String.format("Getting deployment options for bean '%s'", beanName));

        Verticle annotation = applicationContext.findAnnotationOnBean(beanName, Verticle.class);

        String deploymentOptionsBeanName = annotation.deploymentOptionsBeanName();

        DeploymentOptions deploymentOptions;

        if (StringUtils.hasText(deploymentOptionsBeanName)
                && applicationContext.containsBean(deploymentOptionsBeanName)) {

            deploymentOptions = applicationContext.getBean(deploymentOptionsBeanName,
                    DeploymentOptions.class);
            log.info(String.format("Using %s bean as a deployment options (%s) for Verticle %s",
                    deploymentOptionsBeanName,
                    deploymentOptions.toJson(),
                    beanName));
        } else {
            log.info(String.format("Using empty deployment options for Verticle '%s'", beanName));
            deploymentOptions = new DeploymentOptions();
        }

        DeploymentOptions configuredDeploymentOptions = new DeploymentOptions(deploymentOptions); //defencive copy
        optionsConfigurer.configureFromProperties(beanName, configuredDeploymentOptions);
        log.info("Configured deployment options from Environment properties: {}",
                configuredDeploymentOptions.toJson());
        return configuredDeploymentOptions;
    }
}
