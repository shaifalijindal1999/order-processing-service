package com.orderprocessingservice.models.common;

public class ErrorResponse {
    private String code;
    private String failReason;

    // Constructors
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.failReason = message;
    }

    public ErrorResponse() {
        this.code = null;
        this.failReason = null;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getCode() {
        return code;
    }

    public String getFailReason() {
        return failReason;
    }
}

