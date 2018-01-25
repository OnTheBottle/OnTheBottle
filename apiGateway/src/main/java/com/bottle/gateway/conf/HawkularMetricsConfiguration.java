package com.bottle.gateway.conf;

import io.vertx.core.VertxOptions;
import io.vertx.ext.hawkular.AuthenticationOptions;
import io.vertx.ext.hawkular.VertxHawkularOptions;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sergey on 26.01.2017.
 */
@ConditionalOnClass(VertxHawkularOptions.class)
@ConditionalOnProperty(prefix = "metrics.hawkular", name = "enabled")
@AutoConfigureBefore(VertxConfiguration.class)
@Configuration
@Slf4j
public class HawkularMetricsConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "vertxOptions")
    public VertxOptions vertxOptions(HawkularMetricsProperties metricsProperties) {
        log.debug("Creating VertxHawkularOptions... ({})", metricsProperties);
        VertxHawkularOptions metricsOptions = new VertxHawkularOptions()
                .setEnabled(true)
                .setTenant(metricsProperties.getTenant())
                .setHost(metricsProperties.getHost())
                .setPort(metricsProperties.getPort())
                .setAuthenticationOptions(
                        new AuthenticationOptions()
                                .setEnabled(true)
                                .setId(metricsProperties.getId())
                                .setSecret(metricsProperties.getSecret()));
        return new VertxOptions().setMetricsOptions(metricsOptions);
    }

    @Bean
    public HawkularMetricsProperties hawkularMetricsProperties() {
        return new HawkularMetricsProperties();
    }

    @Data
    @ToString(exclude = { "id", "secret" })
    @ConfigurationProperties(prefix = "metrics.hawkular")
    public static class HawkularMetricsProperties {
        private boolean enabled;
        private String tenant;
        private String host;
        private int port;
        private String id;
        private String secret;
    }
}
