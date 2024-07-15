package com.example.service01.messagequeue.listener.user;

import com.example.service01.dto.ExampleDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
public class ExampleEventListener {

    private CountDownLatch latch = new CountDownLatch(10); // CountDownLatch 는 다른 스레드가 작업을 완료할 때까지 기다리는 데 사용되는 동기화 유틸리티
    private List<ExampleDto> payloads = new ArrayList<>();
    private ExampleDto payload = null;

    @KafkaListener(groupId = "example-group", topics = "example")
    public void listen(ConsumerRecord<String, String> message) {
        log.info("Received message: {}", message);
    }

    // record 수신
    @KafkaListener(groupId = "example-group", topics = "example", containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String, ExampleDto> consumerRecord) {
        payload = consumerRecord.value();
        log.info("Received payload: {}", payload);
        payloads.add(payload);
        latch.countDown();
    }

    public List<ExampleDto> getPayloads() {
        return payloads;
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }








}
