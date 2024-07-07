package com.example.service01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {


    @PostMapping("/login")
    public String login() {
        log.info("login is being called");
        return "login";
    }

    @PostMapping("/signup")
    public String signup() {
        log.info("signup is being called");
        return "signup";
    }

    @PostMapping("/info")
    public String info() {
        log.info("info is being called");
        return "info";
    }

}
