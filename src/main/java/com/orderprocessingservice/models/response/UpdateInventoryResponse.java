package com.orderprocessingservice.models.response;

import com.orderprocessingservice.models.common.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInventoryResponse {
    ErrorResponse errorResponse;
    SuccessResponse successResponse;

    public UpdateInventoryResponse(SuccessResponse successResponse) {
        this.successResponse = successResponse;
        this.errorResponse = null;
    }

    public UpdateInventoryResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
        this.successResponse = null;
    }

    public UpdateInventoryResponse() {
        this.errorResponse = null;
        this.successResponse = null;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public SuccessResponse getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(SuccessResponse successResponse) {
        this.successResponse = successResponse;
    }
}
