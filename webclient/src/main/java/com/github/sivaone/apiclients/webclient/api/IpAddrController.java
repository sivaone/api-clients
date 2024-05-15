package com.github.sivaone.apiclients.webclient.api;

import com.github.sivaone.apiclients.webclient.model.IpAddress;
import com.github.sivaone.apiclients.webclient.service.HttpBinClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class IpAddrController {

    private final HttpBinClient httpBinClient;

    public IpAddrController(HttpBinClient httpBinClient) {
        this.httpBinClient = httpBinClient;
    }

    @GetMapping("/ip")
    public Mono<IpAddress> getIp() {
        return httpBinClient.getMyIp();
    }
}
