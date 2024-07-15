package com.example.service01.messagequeue.listener.user;

import com.example.service01.dto.ExampleDto;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ExampleEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, ExampleDto> kafkaDtoTemplate;

    public void send(String message) {
        log.info("Sending message: {}", message);
        kafkaTemplate.send("example", message);
    }

    // 비동기로
    public void sendMessageV2(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("example", message);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send message: {}", message, ex);
            } else {
                log.info("Sent message: {}", message);
            }
        });
    }

    public void send(String topic, ExampleDto dto) {
        log.info("Sending payload: {}, to topic: {}", dto, topic);
        kafkaDtoTemplate.send(topic, dto);
    }


}
