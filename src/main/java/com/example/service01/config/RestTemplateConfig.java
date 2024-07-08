package com.example.service01.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // URL에 Spring Cloud Eureka 서버의 서비스 이름을 사용할 수 있게 해줌 (ip, port 대신 service name으로 호출 가능)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
