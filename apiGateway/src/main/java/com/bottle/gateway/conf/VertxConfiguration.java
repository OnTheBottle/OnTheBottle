package com.bottle.gateway.conf;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PreDestroy;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Sergey on 26.01.2017.
 */
@Configuration
@ConditionalOnExpression("@environment.containsProperty('vertx.clustered') || @environment.containsProperty('vertx.HAEnabled')")
@Slf4j
public class VertxConfiguration {
    private Vertx vertx;

    private boolean clustered;
    private boolean haEnabled;

    private Environment environment;
    private ApplicationContext applicationContext;

    @Autowired
    public VertxConfiguration(@Value("${vertx.clustered:false}") boolean clustered,
                              @Value("${vertx.HAEnabled:false}") boolean haEnabled,
                              Environment environment,
                              ApplicationContext applicationContext) {
        this.clustered = clustered;
        this.haEnabled = haEnabled;
        this.environment = environment;
        this.applicationContext = applicationContext;
    }

    /**
     * Creates Vertx clustered or not depending on the property 'vertx.clustered'
     *
     * @return
     */
    private Vertx createVertx() {
        return clustered || haEnabled
                ? createClusteredVertx()
                : createSimpleVertx();
    }

    private Vertx createSimpleVertx() {
        log.info("Creating Vert.x...");
        return Vertx.vertx(getVertxOptions());
    }

    private Vertx createClusteredVertx() {
        try {
            CompletableFuture<Vertx> future = new CompletableFuture<>();
            log.info("Creating clustered Vertx...");
            Vertx.clusteredVertx(getVertxOptions(), ar -> {
                if (ar.succeeded()) {
                    log.info("Vertx creation is completed!");
                    future.complete(ar.result());
                } else {
                    log.error("Vertx creation failed!", ar.cause());
                    future.completeExceptionally(ar.cause());
                }
            });

            return future.get();
        } catch (Exception e) {
            log.error("Clustered Vertx creation failed", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Overrides defined options with options provided as Environment properties (program argument, for example)
     *
     * @return
     */
    private VertxOptions getVertxOptions() {
        VertxOptions vertxOptions = vertxOptions();
        log.info("Using VertxOptions: {}", vertxOptions.toString());
        optionsConfigurer().configureFromProperties(vertxOptions);
        log.info("Configured VertxOptions from Environment: {}", vertxOptions.toString());
        return vertxOptions;
    }

    /**
     * Exposes the clustered Vert.x instance. We must disable destroy method inference, otherwise
     * Spring will call the {@link Vertx#close()} automatically.
     */
    @Bean(destroyMethod = "")
    public Vertx vertx() {
        vertx = createVertx();

        // We will deploy our verticles using this factory which will create spring aware verticles
        vertx.registerVerticleFactory(springVerticleFactory());
        return vertx;
    }

    @Bean
    public OptionsConfigurer optionsConfigurer() {
        return new OptionsConfigurer(environment);
    }

    @Bean
    public SpringVerticleFactory springVerticleFactory() {
        return new SpringVerticleFactory();
    }

    @Bean
    public VerticleDeployer verticleDeployer() {
        return new VerticleDeployer(
                applicationContext,
                vertx(),
                springVerticleFactory(),
                optionsConfigurer(),
                commonVerticleDeploymentOptions()
        );
    }

    @PreDestroy
    public void close() throws ExecutionException, InterruptedException {
        if (vertx != null) {
            CompletableFuture<Void> future = new CompletableFuture<>();
            log.info("Closing Vertx...");
            vertx.close(ar -> {
                log.info("Vertx was closed.");
                future.complete(null);
            });
            future.get();
        }
    }

    @Bean
    @ConditionalOnMissingBean(name = "vertxOptions")
    public VertxOptions vertxOptions() {
        return new VertxOptions();
    }

    @Bean
    public DeploymentOptions commonVerticleDeploymentOptions() {
        return new DeploymentOptions();
    }
}
