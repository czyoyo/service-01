package com.example.service01.messagequeue.listener.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}, ports = {9092})
class ExampleEventProducerTest {

    @Autowired
    private ExampleEventListener listener;

    @Autowired
    private ExampleEventProducer producer;











}
