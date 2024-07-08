package com.example.service01.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-02")
public interface Service02Client {

    @GetMapping("/dummy")
    String getDummy();

}
