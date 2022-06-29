package com.arvind.robotfactory.model;

public class ValidationOutput {
    private String errorMessage;
    private OrderStatus status;

    public ValidationOutput(OrderStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ValidationOutput(OrderStatus status, String errorMessage) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
