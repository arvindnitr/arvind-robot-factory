package com.arvind.robotfactory.parts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Mobility implements ComponentType {
    WHEELS("F", 30.77f, "Mobility with Wheels"),
    LEGS("G", 55.13f,  "Mobility with Legs"),
    TRACKS("H", 50.00f, "Mobility with Tracks");

    String code;
    float price;
    String displayName;

    Mobility(String code, float price, String displayName){
        this.code = code;
        this.price = price;
        this.displayName = displayName;
    }

    public static ComponentType getComponentTypeFromCode(String code) {
        return Arrays.stream(Mobility.values()).filter(mobility -> mobility.code == code).findFirst().get();
    }

    public static List<String> getAllCodes() {
        return Arrays.stream(Mobility.values()).map(mobility -> mobility.getCode()).collect(Collectors.toList());
    }

    @Override
    public String getComponentType() {
        return "MOBILITY"+"_"+this.name();
    }

    public String getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    public String getDisplayName() {
        return displayName;
    }

}
