package com.bottle.gateway.conf;

import com.google.common.collect.Maps;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Created by Sergey on 26.01.2017.
 */
@Slf4j
public class OptionsConfigurer {

    private static final String VERTX_PREFIX = "vertx.";
    private static final String VERTICLE_PREFIX = "verticle.";
    private static final String VERTICLE_COMMON_PREFIX = "common";
    private static final List<String> ignoredVertxOptions = Arrays.asList("clustered");
    private static final List<String> ignoredVerticleOptions = Arrays.asList();
    private static final List<String> interestedPropertyPrefixes = Arrays.asList(VERTX_PREFIX,
            VERTICLE_PREFIX);

    private Environment environment;
    private Map<String, Object> properties;

    @Autowired
    public OptionsConfigurer(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void populateProperties() {
        MutablePropertySources propertySources = ((AbstractEnvironment) environment)
                .getPropertySources();

        Stream<Map<String, Object>> propertyMapsStream = StreamSupport
                .stream(propertySources.spliterator(), false)
                .filter(propertySource -> propertySource instanceof MapPropertySource)
                .map(propertySource -> ((MapPropertySource) propertySource).getSource());

        Stream<Map.Entry<String, Object>> propertyEntriesStream = propertyMapsStream
                .flatMap(propertyMap -> propertyMap.entrySet().stream());

        properties = propertyEntriesStream
                .filter(this::isInterestedProperty)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean isInterestedProperty(Map.Entry<String, Object> property) {
        return interestedPropertyPrefixes.stream()
                .anyMatch(propertyPrefix -> StringUtils.startsWith(property.getKey(), propertyPrefix));
    }

    /**
     * Mutates parameter with parameters from Environment
     * @param vertxOptions
     */
    public void configureFromProperties(VertxOptions vertxOptions) {
        configureFromProperties(vertxOptions, VERTX_PREFIX, ignoredVertxOptions);
    }

    /**
     * Mutates parameter with parameters from Environment
     * @param deploymentOptions
     */
    public void configureCommonFromProperties(DeploymentOptions deploymentOptions) {
        configureFromProperties(VERTICLE_COMMON_PREFIX, deploymentOptions);
    }

    /**
     * Mutates parameter with parameters from Environment
     * @param verticleName
     * @param deploymentOptions
     */
    public void configureFromProperties(String verticleName, DeploymentOptions deploymentOptions) {
        configureFromProperties(
                deploymentOptions,
                VERTICLE_PREFIX + verticleName + ".",
                ignoredVerticleOptions);
    }

    /**
     * Configures with values from properties
     * @param obj
     * @return configures obj
     */
    private <T> T configureFromProperties(T obj,
                                          String propertyPrefix,
                                          List<String> ignoredOptions) {
        properties.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(propertyPrefix))
                .map(entry -> Maps.immutableEntry(
                        StringUtils.removeStart(entry.getKey(), propertyPrefix),
                        entry.getValue()))
                .filter(entry -> !ignoredOptions.contains(entry.getKey()))
                .forEach(entry -> setProperty(obj, entry.getKey(), entry.getValue()));
        return obj;
    }

    /**
     * Supports java basic types (e.g. String, int, boolean) and JsonObject
     * @param obj
     * @param propertyName
     * @param propertyValue
     */
    private void setProperty(Object obj,
                             String propertyName,
                             Object propertyValue) {

        String setterName = getSetterName(propertyName);

        Optional<Method> setter = Stream.of(obj.getClass().getMethods())
                .filter(method -> method.getName().equals(setterName))
                .findFirst();

        if (!setter.isPresent()) {
            log.warn("Could not find setter '{}'", setterName);
            return;
        }

        Class<?> fieldType = setter.get().getParameterTypes()[0]; //setter must have 1 parameter
        ReflectionUtils.invokeMethod(setter.get(), obj, convertValue(propertyValue, fieldType));
    }

    private Object convertValue(Object propertyValue, Class<?> fieldType) {
        if (fieldType == JsonObject.class) {
            propertyValue = new JsonObject(propertyValue.toString());
        } else {
            propertyValue = ConvertUtils.convert(propertyValue, fieldType);
        }
        return propertyValue;
    }

    private String getSetterName(String propertyName) {
        return "set" + StringUtils.capitalize(propertyName);
    }

}

