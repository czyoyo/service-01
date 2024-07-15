package com.example.service01.messagequeue.listener.user;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service01.dto.ExampleDto;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}, ports = {9092})
class ExampleEventProducerTest {

    private static final Logger log = LoggerFactory.getLogger(ExampleEventProducerTest.class);
    @Autowired
    private ExampleEventListener consumer;

    @Autowired
    private ExampleEventProducer producer;


    @Test
    void testSend() throws InterruptedException {
        String topic = "example";

        ExampleDto payload = ExampleDto.builder()
            .id("1")
            .name("test")
            .build();

        for (int i = 0; i < 10; i++) {
            producer.send(topic, payload);
        }

        // 모든 메시지 수신 기다림
        consumer.getLatch().await(10, TimeUnit.SECONDS);

        assertEquals(10, consumer.getPayloads().size());
        assertEquals(payload, consumer.getPayloads().get(0));

        log.info("Received payloads: {}", consumer.getPayloads());


    }











}
