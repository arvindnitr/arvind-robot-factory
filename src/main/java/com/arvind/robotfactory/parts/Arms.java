package com.arvind.robotfactory.parts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Arms implements ComponentType {
    HANDS("D", 28.94f,  "Arms with Hands"),
    GRIPPERS("E", 12.39f, "Arms with Grippers");

    String code;
    float price;
    String displayName;

    Arms(String code, float price, String displayName) {
        this.code = code;
        this.price = price;
        this.displayName = displayName;
    }

    public static ComponentType getComponentTypeFromCode(String code) {
        return Arrays.stream(Arms.values()).filter(arm -> arm.code == code).findFirst().get();
    }

    public static List<String> getAllCodes() {
        return Arrays.stream(Arms.values()).map(arms -> arms.getCode()).collect(Collectors.toList());
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

    @Override
    public String getComponentType() {
        return "ARMS"+"_"+this.name();
    }
}
