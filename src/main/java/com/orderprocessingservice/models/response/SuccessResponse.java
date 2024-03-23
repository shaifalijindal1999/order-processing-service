package com.orderprocessingservice.models.response;

public class SuccessResponse {
    String message;

    public  SuccessResponse() {
        this.message = null;
    }

    public SuccessResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
