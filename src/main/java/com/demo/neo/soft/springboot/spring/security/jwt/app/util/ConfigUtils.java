package com.demo.neo.soft.springboot.spring.security.jwt.app.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.auth-service")
public class ConfigUtils {
    private String registrationUrl;
    private String loginUrl;
    private String validateUrl;
    private String basicAuth;
}