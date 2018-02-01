package com.bottle.gateway;

import com.bottle.properties.OnTheBottle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.bottle.gateway.conf.VertxConfiguration;
import com.bottle.gateway.conf.WebApp;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
@Import(VertxConfiguration.class)
@SpringBootApplication
@ComponentScan({"com.bottle"})
@EntityScan("com.bottle")
@EnableJpaRepositories("com.bottle")
public class ApiGateway {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ApiGateway.class, args);
        Environment env = applicationContext.getEnvironment();
        log.info("Environment: {}", env);
        log.info("ACTIVE PROFILE: {}", Arrays.toString(env.getActiveProfiles()));
    }

    @Bean
    public DeploymentOptions commonVerticleDeploymentOptions() {
        return new DeploymentOptions().setConfig(new JsonObject());
    }

    @PostConstruct
    public void init() {
        log.info("OnTheBottle properties: {}", onTheBottle());
        log.info("WebApp properties: {}", webappProperties());
    }

    @ConfigurationProperties(prefix = "bottle")
    @Bean
    public OnTheBottle onTheBottle() {
        return new OnTheBottle();
    }

    @ConfigurationProperties(prefix = "webapp")
    @Bean
    public WebApp webappProperties() {
        return new WebApp();
    }

}
