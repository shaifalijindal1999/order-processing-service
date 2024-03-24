package com.orderprocessingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessingservice.models.request.SubmitOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumeSubmitOrderEventService {

    @Autowired
    UpdateInventoryService updateInventoryService;

    public void handleSubmitOrderEvent(String record) throws JsonProcessingException {

        // fetch orderId and event payload from the record
        String orderId = record.split("_")[0];
        String event = record.split("_")[1];

        ObjectMapper objectMapper = new ObjectMapper();

        SubmitOrderRequest productRequest = objectMapper.readValue(event, SubmitOrderRequest.class);

        updateInventoryService.updateInventory(productRequest, orderId);

    }
}
