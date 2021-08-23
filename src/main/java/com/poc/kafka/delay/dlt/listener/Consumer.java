package com.poc.kafka.delay.dlt.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RetryableTopic(
            attempts = "5",
            backoff = @Backoff(delay = 1000, multiplier = 2, maxDelay = 5000)
//            kafkaTemplate =
//            listenerContainerFactory =
    )
    @KafkaListener(
            topics = "some-topic",
            groupId = "some-group-id")
    public void onMessage(String message) {
        log.info("Message received: {}", message);

        if ("exception".equalsIgnoreCase(message)) {
            throw new RuntimeException(message);
        }
    }

    @DltHandler
    public void processMessage(String message) {
        log.info("Message will be send to DLT: {}", message);
    }
}
