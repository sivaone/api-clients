package com.github.sivaone.apiclients.webclient.service;

import com.github.sivaone.apiclients.webclient.model.IpAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class HttpBinClient {

    private final WebClient webClient;

    public HttpBinClient(@Qualifier("proxiedWebClient") WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<IpAddress> getMyIp() {
        return webClient.get()
                .uri("/get")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(IpAddress.class);
    }

}
