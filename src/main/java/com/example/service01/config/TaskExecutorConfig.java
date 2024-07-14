package com.example.service01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class TaskExecutorConfig {

    // 컨슈머 커밋 작업을 비동기로 처리하기 위한 TaskExecutor 빈 등록
    @Bean
    public ThreadPoolTaskExecutor  executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 기본 스레드 수
        executor.setMaxPoolSize(100); // 최대 스레드 수
        executor.setQueueCapacity(200); // 대기 큐 사이즈
        executor.setThreadNamePrefix("kafka-consumer-thread-"); // 스레드 이름 접두사
        return executor;
    }


}
