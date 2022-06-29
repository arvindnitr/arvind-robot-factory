package com.arvind.robotfactory.parts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Face implements ComponentType {
    HUMANOID("A", 10.28f, "Humanoid Face"),
    LCD("B", 24.07f,  "LCD Face"),
    STEAMPUNK("C", 13.30f,  "Steampunk Face");

    String code;
    float price;
    String displayName;

    Face(String code, float price, String displayName){
        this.code = code;
        this.price = price;
        this.displayName = displayName;
    }

    public static ComponentType getComponentTypeFromCode(String code){
        return Arrays.stream(Face.values()).filter(face -> face.code == code).findFirst().get();
    }

    public static List<String> getAllCodes() {
        return Arrays.stream(Face.values()).map(face -> face.getCode()).collect(Collectors.toList());
    }

    @Override
    public String getComponentType() {
        return "FACE"+"_"+this.name();
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
