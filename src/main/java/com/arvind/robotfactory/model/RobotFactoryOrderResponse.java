package com.arvind.robotfactory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RobotFactoryOrderResponse {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String message;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    OrderStatus status;

    @JsonProperty("order_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String orderId;

    @JsonProperty("total")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Float robotCost;

    public RobotFactoryOrderResponse(ValidationOutput validationOutput) {
        this.message = validationOutput.getErrorMessage();
        this.status = validationOutput.getStatus();
    }

    public RobotFactoryOrderResponse(int orderId, float robotCost, OrderStatus status) {
        this.orderId = String.valueOf(orderId);
        this.robotCost = robotCost;
        this.status = status;
    }

    public RobotFactoryOrderResponse(String message, OrderStatus status) {
        this.message = message;
        this.status = status;
    }
}
