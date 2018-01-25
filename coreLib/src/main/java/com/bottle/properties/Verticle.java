package com.bottle.properties;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * Created by Sergey on 26.01.2017.
 */
@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
// Prototype scope is needed because multiple instances of Verticle can be deployed
@Scope(SCOPE_PROTOTYPE)
public @interface Verticle {

    String deploymentOptionsBeanName() default "commonVerticleDeploymentOptions";
}