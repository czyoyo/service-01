package com.example.service01.config;

import com.example.service01.dto.ExampleDto;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ExampleDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "example-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 들어오는 record 를 객체로 받기 위한 deserializer
        JsonDeserializer<ExampleDto> deserializer = new JsonDeserializer<>(ExampleDto.class, false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean                                                  // 나중에 DTO 로 변경해야함
    public ConcurrentKafkaListenerContainerFactory<String, ExampleDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExampleDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // consumer 에서 record 를 필터링 할 수 있음
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ExampleDto> filterKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ExampleDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setRecordFilterStrategy(record -> Integer.parseInt(record.value().getId()) % 2 == 0);
        return factory;
    }





}
