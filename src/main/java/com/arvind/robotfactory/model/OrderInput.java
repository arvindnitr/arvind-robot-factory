package com.arvind.robotfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderInput {

    @JsonProperty("components")
    List<String> parts;

    public OrderInput() {}

    public OrderInput(List<String> parts) {
        this.parts = parts;
    }

    public List<String> getParts() {
        return parts;
    }

    public void setParts(List<String> parts) {
        this.parts = parts;
    }
}
