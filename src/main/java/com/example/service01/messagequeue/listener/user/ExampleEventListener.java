package com.example.service01.messagequeue.listener.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExampleEventListener {

    @KafkaListener(groupId = "example-group", topics = "example")
    public void listen(ConsumerRecord<String, String> message) {
        log.info("Received message: {}", message);
    }





}
