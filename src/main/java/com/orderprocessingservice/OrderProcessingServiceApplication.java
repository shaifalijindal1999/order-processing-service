package com.orderprocessingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderprocessingservice.service.ConsumeSubmitOrderEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import static com.orderprocessingservice.constants.Constants.KAFKA_CONSUMER_GROUP;
import static com.orderprocessingservice.constants.Constants.KAFKA_TOPIC;

@SpringBootApplication
public class OrderProcessingServiceApplication {

    @Autowired
    ConsumeSubmitOrderEventService consumeSubmitOrderEventService;

    private final Logger logger = LoggerFactory.getLogger(OrderProcessingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessingServiceApplication.class, args);
    }

        @KafkaListener(id = KAFKA_CONSUMER_GROUP, topics = KAFKA_TOPIC)
        public void listen(String record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {

        consumeSubmitOrderEventService.handleSubmitOrderEvent(record);

        this.logger.info("Received: {} from {} @ {}", record, topic, offset);

    }
}
