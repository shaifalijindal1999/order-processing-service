package com.orderprocessingservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessingservice.models.request.SubmitOrderRequest;
import com.orderprocessingservice.models.response.UpdateInventoryResponse;
import com.orderprocessingservice.service.ConsumeSubmitOrderEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class OrderProcessingServiceApplication {

    @Autowired
    ConsumeSubmitOrderEventService consumeSubmitOrderEventService;

    private final Logger logger = LoggerFactory.getLogger(OrderProcessingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessingServiceApplication.class, args);
    }

        @KafkaListener(id = "order-acceptance-group", topics = "order-topic")
        public void listen(String record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.OFFSET) long offset) throws JsonProcessingException {


        consumeSubmitOrderEventService.handleSubmitOrderEvent(record);

        this.logger.info("Received: {} from {} @ {}", record, topic, offset);



    }
}
