package com.github.sivaone.apiclients.webclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.ProxyProvider;

import java.net.InetSocketAddress;


@Configuration
@Slf4j
public class ApplicationConfig {

    private final WebclientProperties webclientProperties;

    public ApplicationConfig(WebclientProperties webclientProperties) {
        this.webclientProperties = webclientProperties;
    }

    @Bean("proxiedWebClient")
    public WebClient webClient() {
        log.info("START: Webclient initialization");
        log.info("Properties {}", webclientProperties.toString());
        // Proxy config setup
        final HttpClient httpClient = HttpClient.create()
                .proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP)
                        .address(new InetSocketAddress(webclientProperties.getProxyHost(),
                                webclientProperties.getProxyPort()))
                        .username(webclientProperties.getProxyUser())
                        .password(u -> webclientProperties.getProxyPwd()));

        final ReactorClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        // webclient setup
        WebClient webClient = WebClient.builder()
                .clientConnector(httpConnector)
                .baseUrl(webclientProperties.getBaseUrl())
                .build();

        log.info("END: Webclient initialization");

        return webClient;
    }
}
