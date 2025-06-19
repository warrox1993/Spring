package com.example.Decouverte_Spring_boot.config.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private String issuer;
    private String secret;
    private Long expiration = 60_000L;
}
