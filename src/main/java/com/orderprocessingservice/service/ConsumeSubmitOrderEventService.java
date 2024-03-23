package com.orderprocessingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessingservice.OrderProcessingServiceApplication;
import com.orderprocessingservice.models.request.SubmitOrderRequest;
import com.orderprocessingservice.models.response.UpdateInventoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumeSubmitOrderEventService {

    private final Logger logger = LoggerFactory.getLogger(ConsumeSubmitOrderEventService.class);

    @Autowired
    UpdateInventoryService updateInventoryService;

    public void handleSubmitOrderEvent(String record) throws JsonProcessingException {

        String orderId = record.split("_")[0];
        String event = record.split("_")[1];

        ObjectMapper objectMapper = new ObjectMapper();

        SubmitOrderRequest productRequest = objectMapper.readValue(event, SubmitOrderRequest.class);

        updateInventoryService.updateInventory(productRequest, orderId);

    }
}
