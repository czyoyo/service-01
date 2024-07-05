package com.example.service01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service01")
@Slf4j
@RequiredArgsConstructor
public class DefaultController {

    private final Environment environment;

    @GetMapping("/hello")
    public String hello() {
        log.info("service02 is being called");
        return "Hello from service02 running at port: " + environment.getProperty("local.server.port");
    }

}
