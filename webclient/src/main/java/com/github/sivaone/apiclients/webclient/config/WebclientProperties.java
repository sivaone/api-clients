package com.github.sivaone.apiclients.webclient.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.webclient")
@Getter
@Setter
@ToString
public class WebclientProperties {

    private String baseUrl;
    private String proxyHost;
    private Integer proxyPort;
    private String proxyUser;
    private String proxyPwd;

}
