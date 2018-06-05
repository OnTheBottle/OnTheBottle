package com.bottle.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "upload")
@Configuration("uploadConfig")
@Getter
@Setter
public class UploadConfig {

    private String filePath;
    private String uploadFolder;
}
