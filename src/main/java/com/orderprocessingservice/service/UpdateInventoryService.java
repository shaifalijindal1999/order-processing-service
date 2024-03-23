package com.orderprocessingservice.service;

import com.orderprocessingservice.models.request.SubmitOrderRequest;
import com.orderprocessingservice.models.response.UpdateInventoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UpdateInventoryService {

    private final Logger logger = LoggerFactory.getLogger(UpdateInventoryService.class);
    public void updateInventory(SubmitOrderRequest request, String orderId) {
        WebClient webClient = WebClient.create("http://localhost:8888");

        Mono<UpdateInventoryResponse> responseMono = webClient.patch()
                .uri("http://localhost:8888/update")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UpdateInventoryResponse.class);

        responseMono.subscribe(
                updateInventoryResponse -> {

                    if (updateInventoryResponse.getSuccessResponse() != null) {
                        // we can send a notification to user of order acceptance
                        logger.info("method=processOrder message=order accepted for orderId={}", orderId);
                    }
                    else {
                        logger.error("method=processOrder message=order failed for orderId={} error={}", orderId,
                                updateInventoryResponse.getErrorResponse().getFailReason());
                    }
                },
                error -> {
                    logger.error("method=processOrder message=order failed for orderId={} error={}", orderId, error.getMessage());
                }
        );
    }

}
